package com.xwh.it.exception;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.xwh.it.model.dto.Restful;
import com.xwh.it.model.em.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 全局异常处理
 * @author: 先谢郭嘉
 * @create: 2020-11-22 17:49
 */
@ControllerAdvice
@Slf4j
public class GlobalException {
	/**
	 * 控制台是否打印异常
	 */
	private boolean flag = true;

	/**
	 * 在当前类每个方法进入之前触发的操作
	 * @param request
	 * @throws IOException
	 */
	@ModelAttribute
	public void get(HttpServletRequest request) throws IOException {
		
	}

	/**
	 * 全局异常拦截（拦截项目中的所有异常）
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ExceptionHandler
	public Restful handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 打印堆栈，以供调试
		log.error("全局异常---------------");
		if(flag) e.printStackTrace();

		// 不同异常返回不同状态码
		if (e instanceof NotLoginException) {
			// 如果是未登录异常
			NotLoginException ee = (NotLoginException) e;
			return new Restful<>(ResultCodeEnum.NOT_LOGIN.getFlag(),
					ResultCodeEnum.NOT_LOGIN.getCode(),
					ResultCodeEnum.NOT_LOGIN.getMessage(),ee.getMessage());
		} else if(e instanceof NotRoleException) {
			// 如果是角色异常
			NotRoleException ee = (NotRoleException) e;
			return new Restful<>(ResultCodeEnum.NOT_JUR.getFlag(),
					ResultCodeEnum.NOT_JUR.getCode(),
					ResultCodeEnum.NOT_JUR.getMessage(),"无此角色：" + ee.getRole());
		} else if(e instanceof NotPermissionException) {
			// 如果是权限异常
			NotPermissionException ee = (NotPermissionException) e;
			return new Restful<>(ResultCodeEnum.NOT_JUR.getFlag(),
					ResultCodeEnum.NOT_JUR.getCode(),
					ResultCodeEnum.NOT_JUR.getMessage(),"无此权限：" + ee.getCode());
		} else if(e instanceof DisableLoginException) {
			// 如果是被封禁异常
			DisableLoginException ee = (DisableLoginException) e;
			return new Restful<>(ResultCodeEnum.NOT_JUR.getFlag(),
					ResultCodeEnum.NOT_JUR.getCode(),
					ResultCodeEnum.NOT_JUR.getMessage(),"账号被封禁：" + ee.getDisableTime() + "秒后解封");
		} else {
			// 普通异常, 输出：500 + 异常信息
			return Restful.error().message(e.getMessage());
		}
	}


	/**
	 * 全局异常拦截（拦截项目中的NotLoginException异常）
	 * @param nle
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@ExceptionHandler(NotLoginException.class)
	public Restful<Object> handlerNotLoginException(NotLoginException nle, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 打印堆栈，以供调试
		nle.printStackTrace();
		// 判断场景值，定制化异常信息
		if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
			// 未提供token
			return Restful.no()
					.code(NotLoginException.NOT_TOKEN)
					.message(NotLoginException.NOT_TOKEN_MESSAGE);
		} else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
			// token无效
			return Restful.no()
					.code(NotLoginException.INVALID_TOKEN)
					.message(NotLoginException.INVALID_TOKEN_MESSAGE);
		} else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
			// token已过期
			return Restful.no()
					.code(NotLoginException.TOKEN_TIMEOUT)
					.message(NotLoginException.TOKEN_TIMEOUT_MESSAGE);
		} else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
			// token已被顶下线
			return Restful.no()
					.code(NotLoginException.BE_REPLACED)
					.message(NotLoginException.BE_REPLACED_MESSAGE);
		} else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
			// token已被踢下线
			return Restful.no()
					.code(NotLoginException.KICK_OUT)
					.message(NotLoginException.KICK_OUT_MESSAGE);
		} else {
			// 当前会话未登录
			return Restful.no().message(NotLoginException.DEFAULT_MESSAGE);
		}
	}
	

}
