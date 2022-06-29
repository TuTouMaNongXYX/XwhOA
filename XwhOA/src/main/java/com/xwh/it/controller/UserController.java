package com.xwh.it.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xwh.it.model.dto.Restful;
import com.xwh.it.model.dto.UserVo;
import com.xwh.it.model.entity.User;
import com.xwh.it.model.entity.UserIdentity;
import com.xwh.it.model.entity.UserInformation;
import com.xwh.it.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户基础表 前端控制器
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Api(tags = "用户接口")
@RestController
public class UserController {

    @Resource
    IUserService userService;

    /**
     * @return
     * @description 登录验证
     * @author 谢宇轩
     * @date 2022/6/28 14:49
     * @params
     */
    @ApiOperationSupport(author = "谢宇轩")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public Restful login(@ApiParam(value = "账号") @RequestParam(value = "username") String username,
                         @ApiParam(value = "密码") @RequestParam(value = "password") String password) {
        UserVo userVo = userService.select_loginIs(username, password);
        if (userVo == null) {
            return Restful.error().message("登录失败");
        }
        StpUtil.login(userVo.getUser().getOppnId());
        userVo.setToken(StpUtil.getTokenValue());

        return Restful.ok(userVo).message("登录成功");
    }


    /**
     * @return
     * @description 注册
     * @author 谢宇轩
     * @date 2022/6/28 17:34
     * @params
     */
    @ApiOperationSupport(author = "谢宇轩")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @PostMapping("/registered")
    public Restful registered(@ApiParam(value = "账号") @RequestParam(value = "username") String username,
                              @ApiParam(value = "密码") @RequestParam(value = "password") String password) {
        if (SaFoxUtil.isEmpty(username) || SaFoxUtil.isEmpty(password)) {
            return Restful.error().message("参数错误");
        }
        //是否有同账号
        if (userService.select_isUser(username)) {
            return Restful.error().message("注册失败,已存在该账号");
        }

        boolean is = userService.addUser(username, password);
        if (is) {
            return Restful.ok().message("注册成功");
        } else {
            return Restful.error().message("注册失败");
        }
    }


    /**
     * @return
     * @description 账号是否存在
     * @author 谢宇轩
     * @date 2022/6/29 8:39
     * @params
     */
    @ApiOperationSupport(author = "谢宇轩")
    @ApiOperation(value = "账号是否存在", httpMethod = "GET")
    @GetMapping("/accountVerification")
    public Restful accountVerification(String username) {
        //是否有同账号
        if (userService.select_isUser(username)) {
            return Restful.error().message("注册失败,已存在该账号");
        }
        return Restful.ok().message("可注册");
    }


    /**
     * @return
     * @description 判断用户是否登录
     * @author 谢宇轩
     * @date 2022/6/29 8:41
     * @params
     */
    @ApiOperationSupport(author = "谢宇轩")
    @ApiOperation(value = "判断用户是否登录", httpMethod = "GET")
    @GetMapping("/is")
    public Restful is() {
        if (StpUtil.isLogin()) {
            return Restful.ok(userService.select_ThisUser(StpUtil.getLoginIdAsString())).message("已登录");
        }
        return Restful.error().message("未登录");
    }


}
