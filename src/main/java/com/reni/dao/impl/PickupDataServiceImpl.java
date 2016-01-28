package com.reni.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.PickupDataService;
import com.reni.model.Pickup;
import com.reni.rowmapper.PickupRowMapper;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class PickupDataServiceImpl implements PickupDataService {

	
	private static final String SELECT_PICKUP="SELECT * FROM pickup";
	private static final String SELECT_PICKUP_BY_ID="SELECT * FROM pickup where Pickup_id=:id";
	
	 @Autowired  
	 private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	 public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {  
		  this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;  
	 }
	 
	
	 @SuppressWarnings("unchecked")
	 @Override  
	 public List<Pickup> listOfPickups() {  
		List<Pickup> pickupDetails = (List<Pickup>) namedParameterJdbcTemplate.query(SELECT_PICKUP, new PickupRowMapper()); 
	    System.out.println(pickupDetails);
		return pickupDetails;  
	 }

	@Override
	public void addPickup(Pickup pickup) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pickup getPickup(String pckupId) throws RENIDataServiceException {
   	    SqlParameterSource namedParameters = new MapSqlParameterSource("id", pckupId);
   	    return (Pickup) namedParameterJdbcTemplate.queryForObject(SELECT_PICKUP_BY_ID, namedParameters,  new PickupRowMapper());  
	 }

	@Override
	public void deletePickup(Pickup pickup) throws RENIDataServiceException {
		// TODO Auto-generated method stub
		
	}
	
	/*@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addPickup(Pickup pickup) {
		sessionFactory.getCurrentSession().saveOrUpdate(pickup);
	}

	@Override
	public Pickup getPickup(String empid) {
		System.out.println("in data impl");
		return (Pickup) sessionFactory.getCurrentSession().get(Pickup.class, empid);
	}
	
	public void deletePickup(Pickup pickup){
	//TODO - delete pickup details	
	}
*/	
}
