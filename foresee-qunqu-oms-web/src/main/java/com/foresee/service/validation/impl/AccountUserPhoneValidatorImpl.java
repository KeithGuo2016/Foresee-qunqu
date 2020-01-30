package com.foresee.service.validation.impl;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.foresee.model.User;
import com.foresee.service.AccountsService;
import com.foresee.service.validation.NotAccountUserPhone;
import com.foresee.util.MyApplicationContextUtil;

public class AccountUserPhoneValidatorImpl implements ConstraintValidator<NotAccountUserPhone, String>{
	
    private AccountsService accountsService;
	
	public AccountUserPhoneValidatorImpl() {
		// TODO Auto-generated constructor stub
		ApplicationContext applicationContext = MyApplicationContextUtil.getContext();
		this.accountsService = (AccountsService)applicationContext.getBean(AccountsService.class);
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!StringUtils.isEmpty(value)) {
			User account= new User();
			account.setUserphone(value);
			List<User> list = accountsService.selectListAccount(account);
			if(list.size()==0) {
				return true;
			}
			
		}
		return false;
	}
	
}
