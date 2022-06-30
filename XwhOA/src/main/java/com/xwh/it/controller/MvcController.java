package com.xwh.it.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xwh.it.model.dto.MenuVo;
import com.xwh.it.model.dto.UserVo;
import com.xwh.it.model.entity.Menu;
import com.xwh.it.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 谢宇轩 页面跳转
 * @Description: TODO
 * @DateTime: 2022/6/30 12:59
 **/
@Api(tags = "请求转发接口")
@Controller
@RequestMapping("/mvc")
public class MvcController {

    @Resource
    IMenuService iMenuService;

    /**
     * @return
     * @description 跳转到主页
     * @author 谢宇轩
     * @date 2022/6/30 21:44
     * @params
     */
    @ApiOperationSupport(author = "谢宇轩")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @PostMapping("/index")
    public String index(Model model) {
        //获得当前登录用户id
        String id = StpUtil.getLoginIdAsString();
        //获得当前登录用户目录
        List<MenuVo> menuVoList = iMenuService.selectUserMenu(id);
        model.addAttribute("menuVoList", menuVoList);
        return "oa/index";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "oa/welcome";
    }

}














