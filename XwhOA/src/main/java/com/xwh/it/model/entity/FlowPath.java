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
 * 流程表
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_flow_path")
@ApiModel(value="FlowPath对象", description="流程表")
public class FlowPath implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申请单流水号")
    @TableField("Serial_number")
    private Integer serialNumber;

    @ApiModelProperty(value = "审批人用户唯一标识")
    @TableField("oppo_id")
    private String oppoId;

    @ApiModelProperty(value = "当前进度")
    @TableField("schedule")
    private String schedule;


}
