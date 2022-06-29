package com.xwh.it.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_user_information")
@ApiModel(value="UserInformation对象", description="用户信息表")
public class UserInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一标识")
    @TableField("oppn_id")
    private String oppnId;

    @ApiModelProperty(value = "性别（女0男1）")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "电话")
    @TableField("telephone")
    private String telephone;

    @ApiModelProperty(value = "邮箱")
    @TableField("mailbox")
    private String mailbox;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_at")
    private Date createdAt;

    @ApiModelProperty(value = "更新时间")
    @TableField("updated_at")
    private Date updatedAt;


}
