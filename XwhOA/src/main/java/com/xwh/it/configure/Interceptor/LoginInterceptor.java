package com.xwh.it.configure.Interceptor;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 谢宇轩 登录拦截器
 * @Description: TODO
 * @DateTime: 2022/6/30 21:56
 **/
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行前
     * 该方法在控制器处理请求方法前执行，其返回值表示是否中断后续操作
     * 返回 true 表示继续向下执行，返回 false 表示中断后续操作
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StpUtil.isLogin()) {
            //获得当登录用户id
            String id = StpUtil.getLoginIdAsString();
            //是否被封禁
            if (StpUtil.isDisable(id)) {
                //未登录，返回登陆页
                request.getRequestDispatcher("login").forward(request, response);
                log.info("请求被拦截拉");
                return false;
            }
        }
        //放行
        return true;
    }


    /**
     * 目标方法执行后
     * 该方法在控制器处理请求方法调用之后、解析视图之前执行
     * 可以通过此方法对请求域中的模型和视图做进一步修改
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle执行{}", modelAndView);
    }

    /**
     * 页面渲染后
     * 该方法在视图渲染结束后执行
     * 可以通过此方法实现资源清理、记录日志信息等工作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion执行异常{}", ex);
    }


}
