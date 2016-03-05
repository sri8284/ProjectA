package com.rnei.dao;

import com.rnei.model.Employee;
import com.rnei.service.exception.RENIDataServiceException;

public interface EmployeeDataService {

	Employee getBasicEmployeeInfo(Integer userId) throws RENIDataServiceException;


}
