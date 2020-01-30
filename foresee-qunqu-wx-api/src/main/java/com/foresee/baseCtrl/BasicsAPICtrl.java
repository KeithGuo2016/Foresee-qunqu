package com.foresee.baseCtrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.foresee.utils.RedisOperator;

/**
 * 基础APPCtrl
 * @author DELL
 *
 */
@RestController
public class BasicsAPICtrl {
	
	//redis缓存命名
	protected static final String USER_REDIS_SESSION = "user-redis-session";
	@Autowired
	protected RedisOperator redis;
	
	/**
	 * 获取request对象
	 * @return request
	 */
	protected HttpServletRequest getRequest()
	{
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	}
	
	/**
	 * 获取response对象
	 * @return response
	 */
	protected HttpServletResponse getResponse()
	{
		return ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	/**
	 * 获取session对象
	 * @return session
	 */
	protected HttpSession getSession()
	{
		return getRequest().getSession();
	}
	
	
	//获取当前登录用户信息

}
