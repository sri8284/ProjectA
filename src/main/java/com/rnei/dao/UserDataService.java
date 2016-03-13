package com.rnei.dao;

import com.rnei.model.User;
import com.rnei.service.exception.RENIDataServiceException;

public interface UserDataService {
	User loadUserByUsername(String userId);

}
