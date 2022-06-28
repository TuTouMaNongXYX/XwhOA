package com.xwh.it.configure.satoken;

/**
 * @Author: 谢宇轩
 * @Description: TODO
 * @DateTime: 2022/5/27 14:59
 **/

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwh.it.model.entity.*;
import com.xwh.it.service.IMenuService;
import com.xwh.it.service.IRoleAuthService;
import com.xwh.it.service.IRoleService;
import com.xwh.it.service.IUserIdentityService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    IUserIdentityService userIdentity;
    @Resource
    IMenuService iMenuService;
    @Resource
    IRoleAuthService roleAuthService;
    @Resource
    IRoleService roleService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        UserIdentity user = userIdentity.getOne(new QueryWrapper<UserIdentity>().eq("oppn_id", loginId));
        //权限集合
        List<String> competences = new ArrayList<>();
        //权限角色权限集合
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("role_id", user.getRoleId());
        List<RoleAuth> roleAuthList = roleAuthService.list(wrapper);
        for (RoleAuth role : roleAuthList) {
            QueryWrapper roleWrapper = new QueryWrapper();
            roleWrapper.eq("menu_id", role.getMenuId());
            Menu menu = iMenuService.getOne(roleWrapper);
            competences.add(menu.getMenuAuthName());
        }
        return competences;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        UserIdentity user = userIdentity.getOne(new QueryWrapper<UserIdentity>().eq("oppn_id", loginId));
        //权限角色集合
        List<String> competences = new ArrayList<>();
        //权限角色权限集合
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("role_id", user.getRoleId());
        List<RoleAuth> roleAuthList = roleAuthService.list(wrapper);
        for (RoleAuth role : roleAuthList) {
            QueryWrapper Wrapper = new QueryWrapper();
            Wrapper.eq("menu_id", role.getMenuId());
            Role Role = roleService.getOne(Wrapper);
            competences.add(Role.getRoleCode());
        }
        return competences;
    }


}










