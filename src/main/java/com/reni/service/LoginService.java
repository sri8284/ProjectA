package com.reni.service;

import com.reni.model.Employee;
import com.reni.model.Login;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;

public interface LoginService {

	Employee getBasicEmployeeInfo(Login loginDetails) throws RENIServiceException;

	
}
