package com.xwh.it.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwh.it.mapper.*;
import com.xwh.it.model.dto.MenuVo;
import com.xwh.it.model.dto.Restful;
import com.xwh.it.model.entity.*;
import com.xwh.it.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    MenuMapper menuMapper;
    @Resource
    UserIdentityMapper userIdentityMapper;
    @Resource
    RoleAuthMapper roleAuthMapper;

    //生成目录
    @Override
    public List<MenuVo> selectUserMenu(String id) {
        //获得该用户所有一级目录
        //获得当前用户对象
        QueryWrapper userWrapper = new QueryWrapper();
        userWrapper.eq("oppn_id", id);
        UserIdentity userIdentity = userIdentityMapper.selectOne(userWrapper);
        //获得当前权限角色权限列表
        QueryWrapper roleWrapper = new QueryWrapper();
        roleWrapper.eq("role_id", userIdentity.getRoleId());
        List<RoleAuth> roleAuth = roleAuthMapper.selectList(roleWrapper);
        List<String> menuId = new ArrayList<>();
        for (RoleAuth role : roleAuth) {
            menuId.add(role.getMenuId());
        }
        //生成一级目录
        QueryWrapper<Menu> menuWrapper = new QueryWrapper();
        menuWrapper.in("menu_id", menuId).eq("parent_menu_id", 0).eq("menu_status", true);
        List<Menu> menus = menuMapper.selectList(menuWrapper);

        //生成树形目录
        //初始化树形目录
        List<MenuVo> menuVoList = new ArrayList<>();
        for (Menu menu : menus) {
            MenuVo menuVo = new MenuVo();
            menuVo.setMenu(menu);
            //生成二级目录
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("parent_menu_id", menu.getMenuId());
            List<Menu> list = menuMapper.selectList(wrapper);
            if (list == null) {
                continue;
            }
            menuVo.setMenus(list);
            menuVoList.add(menuVo);
        }
        return menuVoList;
    }


}
