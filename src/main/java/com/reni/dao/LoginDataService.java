package com.reni.dao;

import com.reni.service.exception.RENIDataServiceException;

public interface LoginDataService {

	String createSession(Integer userId) throws RENIDataServiceException;
	boolean isUserSessionExist(Integer userId) throws RENIDataServiceException;
	boolean isValidUser(Integer userId, String password) throws RENIDataServiceException;

}
