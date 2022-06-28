package com.xwh.it.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
@TableName("system_logs")
@ApiModel(value="Logs对象", description="")
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志uuid")
    @TableId("log_id")
    private String logId;

    @ApiModelProperty(value = "操作时间")
    @TableField("log_time")
    private Date logTime;

    @ApiModelProperty(value = "线程id")
    @TableField("thread_id")
    private String threadId;

    @ApiModelProperty(value = "线程名称")
    @TableField("thread_name")
    private String threadName;

    @ApiModelProperty(value = "ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "访问url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "http方法")
    @TableField("http_method")
    private String httpMethod;

    @ApiModelProperty(value = "类方法")
    @TableField("class_method")
    private String classMethod;

    @ApiModelProperty(value = "方法名")
    @TableField("method_name")
    private String methodName;

    @ApiModelProperty(value = "请求参数")
    @TableField("request_params")
    private String requestParams;

    @ApiModelProperty(value = "返回参数")
    @TableField("result")
    private String result;

    @ApiModelProperty(value = "接口耗时")
    @TableField("time_cost")
    private Long timeCost;

    @ApiModelProperty(value = "操作系统")
    @TableField("os")
    private String os;

    @ApiModelProperty(value = "浏览器")
    @TableField("browser")
    private String browser;

    @ApiModelProperty(value = "标识信息")
    @TableField("userAgent")
    private String userAgent;

    @ApiModelProperty(value = "用户唯一标识")
    @TableField("open_id")
    private String openId;


}
