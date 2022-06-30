package com.xwh.it.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xwh.it.model.dto.Restful;
import com.xwh.it.model.entity.Menu;
import com.xwh.it.service.IMenuService;
import com.xwh.it.utils.Number.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Api(tags = "目录接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    IMenuService menuService;






}
