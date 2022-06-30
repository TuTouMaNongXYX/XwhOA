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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基础表 前端控制器
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Api(tags = "用户接口")
@Controller
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
    public String login(@ApiParam(value = "账号") @RequestParam(value = "username") String username,
                        @ApiParam(value = "密码") @RequestParam(value = "password") String password, Model model, HttpServletRequest request) {
        UserVo userVo = userService.select_loginIs(username, password);
        if (userVo == null) {
            return "login";
        }
        StpUtil.login(userVo.getUser().getOppnId());
        //密码清楚
        userVo.getUser().setPassword("");
        request.getSession().setAttribute("thisUser",userVo);
        return "forward:/mvc/index";
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
    public String registered(@ApiParam(value = "账号") @RequestParam(value = "username") String username,
                             @ApiParam(value = "密码") @RequestParam(value = "password") String password,
                             Model model) {
        //返回前端消息
        String msg;
        if (SaFoxUtil.isEmpty(username) || SaFoxUtil.isEmpty(password)) {
            msg = "账号密码为空";
        }
        //是否有同账号
        if (userService.select_isUser(username)) {
            msg = "该账号已注册";
        }
        boolean is = userService.addUser(username, password);
        if (is) {
            msg = "注册成功";
        } else {
            msg = "注册失败";
        }
        model.addAttribute("msg", msg);
        return "login";

    }


}
