package com.xwh.it.configure.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: 谢宇轩  全局异常处理类
 * @Description: TODO
 * @DateTime: 2022/6/28 10:33
 **/
@ControllerAdvice
public class GlobalExceptionHandler {


     /** @description 系统异常处理
     * @author 谢宇轩
     * @date 2022/6/28 10:34
     * @params
     * @return
     */
     @ExceptionHandler(Exception.class)
     public String error(Exception e, Model model) {
         model.addAttribute("exception",e.getMessage());
         return "";
     }


}
