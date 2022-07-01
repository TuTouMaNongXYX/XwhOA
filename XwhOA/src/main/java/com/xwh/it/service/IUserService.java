package com.xwh.it.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwh.it.model.dto.UserVo;
import com.xwh.it.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户基础表 服务类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
public interface IUserService extends IService<User> {


    /**
     * @return
     * @description 登录验证
     * @author 谢宇轩
     * @date 2022/6/28 15:03
     * @params
     */
    UserVo select_loginIs(String username, String password);


    /**
     * @return
     * @description 注册
     * @author 谢宇轩
     * @date 2022/6/28 17:41
     * @params
     */
    boolean addUser(String username, String password);


    /**
     * @return
     * @description 根据用户名查询是否有数据
     * @author 谢宇轩
     * @date 2022/6/28 18:05
     * @params
     */
    boolean select_isUser(String username);


    /**
     * @return
     * @description 已登录用户信息
     * @author 谢宇轩
     * @date 2022/6/29 10:49
     * @params
     */
    UserVo select_ThisUser(String oppnId);


    /**
     * @return
     * @description 分页查询用户信息
     * @author 谢宇轩
     * @date 2022/7/1 4:34
     * @params
     */
    List<User> userList();

}
