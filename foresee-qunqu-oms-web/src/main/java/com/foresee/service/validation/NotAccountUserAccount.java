package com.foresee.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.foresee.service.validation.impl.AccountUserAccountValidatorImpl;

import java.lang.annotation.*;
/*
 *Account Useraccount自定义校验 
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER})   // 约束注解应用的目标元素类型(METHOD, FIELD, TYPE, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER等)
@Retention(RetentionPolicy.RUNTIME)   // 约束注解应用的时机
@Documented
@Constraint(validatedBy ={AccountUserAccountValidatorImpl.class})  // 与约束注解关联的验证器
public @interface NotAccountUserAccount {

    String message() default "该账号已存在";   // 约束注解验证时的输出消息

    Class<?>[] groups() default { };  // 约束注解在验证时所属的组别

    Class<? extends Payload>[] payload() default { }; // 约束注解的有效负载

    @Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER})   // 约束注解应用的目标元素类型(METHOD, FIELD, TYPE, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER等)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    	NotAccountUserAccount[] value();
    }
}