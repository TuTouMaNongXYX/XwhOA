package com.xwh.it.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwh.it.mapper.UserIdentityMapper;
import com.xwh.it.mapper.UserInformationMapper;
import com.xwh.it.model.dto.UserVo;
import com.xwh.it.model.entity.User;
import com.xwh.it.mapper.UserMapper;
import com.xwh.it.model.entity.UserIdentity;
import com.xwh.it.model.entity.UserInformation;
import com.xwh.it.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwh.it.utils.Number.IdUtil;
import com.xwh.it.utils.encryption.Md5Util;
import com.xwh.it.utils.encryption.Sha1Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

/**
 * <p>
 * 用户基础表 服务实现类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;
    @Resource
    UserIdentityMapper userIdentityMapper;
    @Resource
    UserInformationMapper informationMapper;


    //登录验证
    @Override
    public UserVo select_loginIs(String username, String password) {
        password = encryption(password, userMapper.GetUserSalt(username));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username).eq("password", password);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return null;
        }
        //封禁判断
        if (StpUtil.isDisable(user.getOppnId())) {
            return null;
        }
        //获得当前用户信息/身份权限
        QueryWrapper userWrapper = new QueryWrapper();
        userWrapper.eq("oppn_id", user.getOppnId());
        UserIdentity userIdentity = userIdentityMapper.selectOne(userWrapper);
        UserInformation userInformation = informationMapper.selectOne(userWrapper);

        //状态判断
        if (!userIdentity.isState()) {
            return null;
        }
        user.setPassword("");
        UserVo userVo = new UserVo(user, userInformation, userIdentity);
        return userVo;
    }

    //注册用户
    @Override
    public boolean addUser(String username, String password) {
        User user = new User();
        Random random = new Random();
        user.setSalt(random.nextInt(10) + 1);
        user.setUsername(username);
        user.setPassword(encryption(password, user.getSalt()));

        user.setOppnId("user" + IdUtil.getUUIDTOLong());
        Integer is = userMapper.insert(user);
        if (is > 0) {
            UserIdentity userIdentity = new UserIdentity();
            UserInformation userInformation = new UserInformation();
            userIdentity.setOppnid(user.getOppnId());
            //新注册用户默认未禁用
            userIdentity.setState(false);
            userInformation.setOppnId(user.getOppnId());
            Integer i = userIdentityMapper.insert(userIdentity);
            Integer b = informationMapper.insert(userInformation);
            if (i > 0 && b > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean select_isUser(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public UserVo select_ThisUser(String oppnId) {
        QueryWrapper userWrapper = new QueryWrapper();
        userWrapper.eq("oppn_id", oppnId);
        User user = userMapper.selectOne(userWrapper);
        UserIdentity userIdentity = userIdentityMapper.selectOne(userWrapper);
        UserInformation userInformation = informationMapper.selectOne(userWrapper);
        return new UserVo(user,userInformation,userIdentity);
    }


    //密码加密
    public String encryption(String password, Integer salt) {
        for (int i = 0; i < salt; i++) {
            password = Md5Util.md5(password);
            if (i == salt - 1) {
                password = Md5Util.md5(Md5Util.md5(password));
            }
        }
        return password;
    }


}
