package com.xwh.it.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xwh.it.model.entity.User;
import com.xwh.it.model.entity.UserIdentity;
import com.xwh.it.model.entity.UserInformation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 谢宇轩  用户返回视图
 * @Description: TODO
 * @DateTime: 2022/6/28 15:13
 **/
@Data
public class UserVo {

   private User user;
   private UserInformation information;
   private UserIdentity userIdentity;
   private String token;

    public UserVo(User user, UserInformation information, UserIdentity userIdentity) {
        this.user = user;
        this.information = information;
        this.userIdentity = userIdentity;
    }
}
