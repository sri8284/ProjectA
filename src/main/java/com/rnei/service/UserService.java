package com.rnei.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rnei.model.Employee;
import com.rnei.model.Login;
import com.rnei.model.User;
import com.rnei.service.exception.RENIServiceException;

public interface UserService extends UserDetailsService{

	Employee getBasicEmployeeInfo(String userId) throws RENIServiceException;
	User loadUserByUsername(String userId);

}
