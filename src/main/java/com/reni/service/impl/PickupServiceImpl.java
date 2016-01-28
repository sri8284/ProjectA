package com.reni.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.PickupDataService;
import com.reni.model.Pickup;
import com.reni.service.PickupService;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class PickupServiceImpl implements PickupService {

	@Autowired
	PickupDataService pickupServcie;
	
	@Override
	public List<Pickup> getPickupDetails() throws RENIServiceException {
		
		List<Pickup> pickupRespDetails = new ArrayList<Pickup>();
		try {
			return pickupServcie.listOfPickups();
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			//TODO - thow the exception
		}
		
		return pickupRespDetails;
	}

	@Override
	public Pickup getPickupById(String pickupId) throws RENIServiceException {
		Pickup pickupResp = new Pickup();
		try {
			return pickupServcie.getPickup(pickupId);
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			// TODO throws the serviceException
		}
		
		return pickupResp;
	}

	@Override
	public void createPickup(Pickup pickupDetails) throws RENIServiceException {
		try {
			pickupServcie.addPickup(pickupDetails);
		} catch (RENIDataServiceException e) {
			// TODO throws the serviceException
		}
		
	}

}
