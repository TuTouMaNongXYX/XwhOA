package com.xwh.it.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

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
@Validated
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
    public String login(@NotBlank(message = "参数异常")@ApiParam(value = "账号") @RequestParam(value = "username") String username,
                        @NotBlank(message = "参数异常")@ApiParam(value = "密码") @RequestParam(value = "password") String password, Model model, HttpServletRequest request) {
        UserVo userVo = userService.select_loginIs(username, password);
        if (userVo == null) {
            model.addAttribute("msg", "登录失败！");
            return "login";
        }
        StpUtil.login(userVo.getUser().getOppnId());
        //密码清楚
        userVo.getUser().setPassword("");
        request.getSession().setAttribute("thisUser", userVo);
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
    public String registered(@NotBlank(message = "参数异常")@ApiParam(value = "账号") @RequestParam(value = "username") String username,
                             @NotBlank(message = "参数异常")@ApiParam(value = "密码") @RequestParam(value = "password") String password,
                             Model model) {
        //返回前端消息
        String msg;
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
        model.addAttribute("LoginMsg", msg);
        return "login";

    }

    /**
     * @return
     * @description 用户列表
     * @author 谢宇轩
     * @date 2022/7/1 3:41
     * @params
     */
    @ApiOperationSupport(author = "谢宇轩")
    @SaCheckPermission("/user/list")
    @GetMapping("/user/list")
    public String userList(@RequestParam(value = "pageNum",
            defaultValue = "1", required = false) int pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        model.addAttribute("data", new PageInfo<>(userService.list()));
        model.addAttribute("url", "/user/list?pageNum");
        return "oa/user-list";
    }


}
