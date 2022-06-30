package com.xwh.it.service;

import com.xwh.it.model.dto.MenuVo;
import com.xwh.it.model.dto.Restful;
import com.xwh.it.model.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
public interface IMenuService extends IService<Menu> {


     /** @description 生成目录
     * @author 谢宇轩
     * @date 2022/6/29 13:56
     * @params
     * @return
     */
    List<MenuVo> selectUserMenu(String id);



}
