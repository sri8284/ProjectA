package com.rnei.dao.impl;

import static com.rnei.common.util.CommonUtil.currentTimeStamp;
import static com.rnei.common.util.CommonUtil.endTimeStamp;
import static com.rnei.common.util.CommonUtil.generateSessionId;
import static com.rnei.service.constants.RENIDataConstants.ACCESS_KEY;
import static com.rnei.service.constants.RENIDataConstants.END_TIME;
import static com.rnei.service.constants.RENIDataConstants.PASSWORD;
import static com.rnei.service.constants.RENIDataConstants.START_TIME;
import static com.rnei.service.constants.RENIDataConstants.USER_ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.UserDataService;
import com.rnei.model.Pickup;
import com.rnei.model.Role;
import com.rnei.model.User;
import com.rnei.rowmapper.PickupRowMapper;
import com.rnei.rowmapper.StringRowMapper;
import com.rnei.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDataServiceImpl implements UserDataService {

	private static final String SELECT_USER_BY_USERID = "SELECT USER_ID, PASSWORD FROM USER WHERE USER_ID=:USER_ID ";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public User loadUserByUsername(String userId) {
		
		Map<String,Object> namedParameters = new HashMap<String,Object>();
		namedParameters.put(USER_ID, userId);
		
		return (User) namedParameterJdbcTemplate.query(SELECT_USER_BY_USERID, namedParameters,
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User(); 
						user.setUsername(rs.getString(USER_ID));
						 user.setPassword(rs.getString(PASSWORD));
						 Role r = new Role();
					        r.setName("ROLE_USER");
					        List<Role> roles = new ArrayList<Role>();
					        roles.add(r);
					        user.setAuthorities(roles);
					       
						return user;
					}
				});
		//"$2a$10$Bf20bKg5uGZro6F1rg0uduoPg.YTYDhW4hAtuEGxgy.yaQQzxJ2li")
	}
}
