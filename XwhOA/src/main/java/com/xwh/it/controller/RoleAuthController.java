package com.xwh.it.controller;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色权限关联 前端控制器
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Api(tags = "权限角色接口")
@RestController
@Validated
@RequestMapping("/role-auth")
public class RoleAuthController {

}
