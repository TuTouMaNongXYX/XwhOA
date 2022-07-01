package com.xwh.it.controller;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Api(tags = "岗位接口")
@RestController
@Validated
@RequestMapping("/post")
public class PostController {

}
