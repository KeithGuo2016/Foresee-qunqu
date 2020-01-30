package com.foresee.advice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.foresee.model.Log;
import com.foresee.model.User;
import com.foresee.service.LogService;

/**
 * @author wrh
 * 日志记录
 */
@Aspect
@Component
public class WebLogAspect {
	@Resource LogService logService;
	private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    @Pointcut("execution(public * com.foresee.controller..*(..)) && @annotation(com.foresee.advice.WebLog)" )
    public void webLog() {}

    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        User account =  (User) request.getSession().getAttribute("accounts");
        Log log = new Log();
        log.setCreateTime(new Date());
        log.setLogClassMethod(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.setLogDescription(methodDescription);
        log.setLogIp(request.getRemoteAddr());
        log.setLogMethod(request.getMethod());
        log.setLogUrl(request.getRequestURL().toString());
        if(account!=null){
        	log.setLogOperationId(account.getId());
            log.setLogOperationName(account.getUsername());
        }
        log.setLogParameter(JSON.toJSONString(request.getParameterMap()));
        cachedThreadPool.execute(new saveLog(log));
    }
    
    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
    
    
	class saveLog implements Runnable{
	    	
	    	private Log log;
	
			public saveLog(Log log) {
				this.log = log;
			}
	
			@Override
			public void run() {
				try {
					logService.insertSelective(log);	
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			  }
	  }
}
