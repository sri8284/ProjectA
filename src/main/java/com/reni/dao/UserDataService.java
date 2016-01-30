package com.reni.dao;

import com.reni.service.exception.RENIDataServiceException;

public interface UserDataService {

	String createSession(Integer userId) throws RENIDataServiceException;
	boolean isUserSessionExist(Integer userId) throws RENIDataServiceException;
	boolean isValidUser(Integer userId, String password) throws RENIDataServiceException;
	void deleteSession(Integer userId) throws RENIDataServiceException;
	boolean isSessionValid(Integer userId, String sessionId) throws RENIDataServiceException;

}
