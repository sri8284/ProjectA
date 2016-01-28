package com.reni.dao.impl;

import static com.reni.common.util.CommonUtil.currentTimeStamp;
import static com.reni.common.util.CommonUtil.endTimeStamp;
import static com.reni.common.util.CommonUtil.generateSessionId;
import static com.reni.data.constants.RENIDataConstants.END_TIME;
import static com.reni.data.constants.RENIDataConstants.PASSWORD;
import static com.reni.data.constants.RENIDataConstants.SESSION_ID;
import static com.reni.data.constants.RENIDataConstants.START_TIME;
import static com.reni.data.constants.RENIDataConstants.USER_ID;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.LoginDataService;
import com.reni.rowmapper.StringRowMapper;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class LoginDataServiceImpl implements LoginDataService {

	private static final String SELECT_SESSION_BY_USERID = "SELECT SESSION_ID FROM SESSION WHERE USER_ID=:USER_ID";
	private static final String INSERT_SESSION = "INSERT INTO SESSION (USER_ID,SESSION_ID, START_TIME, END_TIME) "
			+ "VALUES (:USER_ID,:SESSION_ID,:START_TIME,:END_TIME)";
	private static final String CHECK_SESSION_EXPIRE = "SELECT COUNT(*) FROM SESSION WHERE USER_ID=:USER_ID AND SESSION_ID=:SESSION_ID AND END_TIME >=:END_TIME";
	private static final String DELETE_SESSION = "DELETE FROM SESSION WHERE USER_ID=:USER_ID AND SESSION_ID=:SESSION_ID";
	private static final String CHECK_USER_VALID = "SELECT COUNT(*) FROM USER WHERE USER_ID=:USER_ID AND PASSWORD=:PASSWORD";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public String createSession(Integer userId) throws RENIDataServiceException {
		Map<String,Object> namedParameters = new HashMap<String,Object>();
		final String sessionId = generateSessionId();
		namedParameters.put(USER_ID, userId);
		namedParameters.put(SESSION_ID, sessionId);
		namedParameters.put(START_TIME, currentTimeStamp());
		namedParameters.put(END_TIME, endTimeStamp());
		namedParameterJdbcTemplate.update(INSERT_SESSION, namedParameters);
		return sessionId;
	}

	@Override
	public boolean isUserSessionExist(Integer userId) throws RENIDataServiceException {
		String sessionId = getSessionByUserId(userId);
		if (sessionId == null) {
			return false;
		}
		if(isSessionExpired(userId,sessionId)){
			return true;
		}else{
			deleteSession(userId,sessionId);
			return false;
		}
	}

	private String getSessionByUserId(Integer userId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource(USER_ID, userId);
		return namedParameterJdbcTemplate.query(SELECT_SESSION_BY_USERID, namedParameters,
				new StringRowMapper());
	}
	
	private boolean isSessionExpired(Integer userId, String sessionId) {
		Map<String,Object> namedParameters = new HashMap<String,Object>();
		namedParameters.put(USER_ID, userId);
		namedParameters.put(SESSION_ID, sessionId);
		namedParameters.put(END_TIME,  currentTimeStamp());
		final int value = namedParameterJdbcTemplate.queryForObject(CHECK_SESSION_EXPIRE, namedParameters,
				Integer.class);
		if(value==0){
			return false;
		}else
			return true;
	}
	
	private void deleteSession(Integer userId, String sessionId) {
		Map<String,Object> namedParameters = new HashMap<String,Object>();
		namedParameters.put(USER_ID, userId);
		namedParameters.put(SESSION_ID, sessionId);
		namedParameterJdbcTemplate.update(DELETE_SESSION, namedParameters);
	}

	@Override
	public boolean isValidUser(Integer userId, String password) throws RENIDataServiceException {
		Map<String,Object> namedParameters = new HashMap<String,Object>();
		namedParameters.put(USER_ID, userId);
		namedParameters.put(PASSWORD, password);
		final int value = namedParameterJdbcTemplate.queryForObject(CHECK_USER_VALID, namedParameters,
				Integer.class);
		if(value==0){
			return false;
		}else{
			return true;
		}
	}
}
