package com.reni.dao;

import com.reni.model.Employee;
import com.reni.service.exception.RENIDataServiceException;

public interface EmployeeDataService {

	Employee getBasicEmployeeInfo(Integer userId) throws RENIDataServiceException;

}
