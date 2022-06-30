package com.xwh.it.model.dto;

import com.xwh.it.model.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * @Author: 谢宇轩 目录视图
 * @Description: TODO
 * @DateTime: 2022/6/29 13:52
 **/
@Data
public class MenuVo {
    //父目录
    private Menu menu;
    //子目录
    private List<Menu> menus;
}
