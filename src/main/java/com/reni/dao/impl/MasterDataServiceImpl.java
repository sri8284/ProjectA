package com.reni.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.model.Area;
import com.reni.model.Item;
import com.reni.model.OnRoadResource;
import com.reni.model.Vendor;
import com.reni.rowmapper.AreaRowMapper;
import com.reni.rowmapper.ItemRowMapper;
import com.reni.rowmapper.ORRRowMapper;
import com.reni.rowmapper.VendorRowMapper;
import com.reni.service.MasterDataService;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class MasterDataServiceImpl implements MasterDataService {

	private static final String SELECT_AREA ="SELECT * FROM AREA" ;
	private static final String SELECT_ITEM = "SELECT * FROM ITEM";
	private static final String SELECT_ORR = "SELECT * FROM ORR";
	private static final String SELECT_VENDOR = "SELECT * FROM VENDOR";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public List<Area> getAreas() throws RENIDataServiceException {
		return (List<Area>) namedParameterJdbcTemplate.query(SELECT_AREA, new AreaRowMapper()); 
	}

	@Override
	public List<Item> getItems() throws RENIDataServiceException {
		return (List<Item>) namedParameterJdbcTemplate.query(SELECT_ITEM, new ItemRowMapper());
	}

	@Override
	public List<OnRoadResource> getORRDetails() throws RENIDataServiceException {
		return (List<OnRoadResource>) namedParameterJdbcTemplate.query(SELECT_ORR, new ORRRowMapper());
	}

	@Override
	public List<Vendor> getVendors() throws RENIDataServiceException {
		return (List<Vendor>) namedParameterJdbcTemplate.query(SELECT_VENDOR, new VendorRowMapper());
	}

}
