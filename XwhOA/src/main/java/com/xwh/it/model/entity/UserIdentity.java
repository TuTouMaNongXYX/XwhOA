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
 * 用户身份表
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_user_identity")
@ApiModel(value = "UserIdentity对象", description = "用户身份表")
public class UserIdentity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一标识")
    @TableField("oppo_id")
    private String oppoId;

    @ApiModelProperty(value = "部门code")
    @TableField("department_code")
    private String departmentCode;

    @ApiModelProperty(value = "岗位id")
    @TableField("post_id")
    private Integer postId;

    @ApiModelProperty(value = "权限角色")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "当前状态（布尔类型）")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "薪资")
    @TableField(" salary")
    private Integer salary;

}
