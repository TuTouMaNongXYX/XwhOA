package com.xwh.it.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("system_menu")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private BigDecimal sort;

    @ApiModelProperty(value = "菜单id")
    @TableId("menu_id")
    private String menuId;

    @ApiModelProperty(value = "菜单编码")
    @TableField("menu_path")
    private String menuPath;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "菜单权限码")
    @TableField("menu_auth_name")
    private String menuAuthName;

    @ApiModelProperty(value = "菜单等级")
    @TableField("menu_level")
    private Integer menuLevel;

    @ApiModelProperty(value = "父级菜单id(0为一级菜单)")
    @TableField("parent_menu_id")
    private String parentMenuId;

    @ApiModelProperty(value = "菜单状态，1正常，0停用")
    @TableField("menu_status")
    private boolean menuStatus;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;


}
