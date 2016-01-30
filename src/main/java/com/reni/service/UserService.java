package com.reni.service;

import com.reni.model.Employee;
import com.reni.model.Login;
import com.reni.service.exception.RENIServiceException;

public interface UserService {

	Employee getBasicEmployeeInfo(Login loginDetails) throws RENIServiceException;

	void deleteSession(Integer userId) throws RENIServiceException;

	
}
