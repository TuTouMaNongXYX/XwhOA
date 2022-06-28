package com.xwh.it.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_department")
@ApiModel(value="Department对象", description="部门表")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "部门代码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "上级部门id")
    @TableField("superior")
    private String superior;

    @ApiModelProperty(value = "是否为顶级部门")
    @TableField("istop")
    private String istop;

    @ApiModelProperty(value = "备注")
    @TableField("note")
    private String note;

    @ApiModelProperty(value = "状态")
    @TableField("state")
    private String state;


}
