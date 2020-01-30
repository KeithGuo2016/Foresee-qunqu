package com.foresee.advice;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.foresee.model.User;

/**
 * 登录拦截注解自定义实现
 * @author wrh
 *
 */
@Aspect
@Component
public class AccountAuthenticationAdvice {
	
    @Pointcut("execution(public * com.foresee.controller..*(..)) && @annotation(com.foresee.advice.AccountAuthenticationAnnotation)" )
    public void interceptorAdvice(){}  
    
    @Before("interceptorAdvice()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        User account =  (User) request.getSession().getAttribute("accounts");
        if(account == null ){
        	response.sendRedirect("/login.html"); //跳转登录页面
        }
        
    }
}