package com.foresee.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.foresee.exception.ResultException;
import com.foresee.utils.Result;
import com.foresee.utils.ResultCode;
import com.foresee.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 错误统一处理
 * @author wrh
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice implements ResponseBodyAdvice<Object> {

	@ExceptionHandler(ResultException.class)
    public Result<?> handleResultException(ResultException e, HttpServletRequest request) {
        return ResultUtils.warn(e.getResultCode());
    }
	
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e, HttpServletRequest request) {
    	e.printStackTrace();
        return ResultUtils.warn(ResultCode.FAIL);
    }
    

    @Override
    public boolean supports(MethodParameter returnType, 
    						@SuppressWarnings("rawtypes") Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, 
    							  MethodParameter returnType, 
    							  MediaType selectedContentType,
                                  @SuppressWarnings("rawtypes") Class selectedConverterType, 
                                  ServerHttpRequest request, 
                                  ServerHttpResponse response) {
        return body;
    }
}
