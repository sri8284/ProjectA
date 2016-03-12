package com.rnei.service;

import com.rnei.model.Employee;
import com.rnei.model.Login;
import com.rnei.service.exception.RENIServiceException;

public interface UserService {

	Employee getBasicEmployeeInfo(Integer userId) throws RENIServiceException;

	void deleteSession(Integer userId) throws RENIServiceException;

	
}
