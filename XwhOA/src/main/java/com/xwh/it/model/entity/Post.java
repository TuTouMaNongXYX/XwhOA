package com.xwh.it.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 岗位表
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
@TableName("system_post")
@ApiModel(value="Post对象", description="岗位表")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "状态")
    @TableField("state")
    private String state;


}
