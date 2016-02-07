package com.reni.service.impl;

import static com.reni.service.constants.RENIServiceConstant.*;
import static com.reni.service.constants.RENIServiceConstant.INVALID_ACCESSS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.PickupDataService;
import com.reni.dao.UserDataService;
import com.reni.model.Pickup;
import com.reni.service.PickupService;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;
import com.reni.service.exception.RENIValidationException;

@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
public class PickupServiceImpl implements PickupService {

	@Autowired
	UserDataService userDataService;
	
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
	public void createPickup(Integer userId,Pickup pickupDetails,String sessionId) throws RENIServiceException {
		try {
			if (!userDataService.isSessionValid(userId,sessionId)) {
				throw new RENIValidationException(RENIErrorCodes.INVALID_ACCESSS, INVALID_ACCESSS);
			}
			if(!userDataService.isAdmin(userId)){
				throw new RENIValidationException(RENIErrorCodes.INVALID_RIGHTS, INVALID_RIGHTS);
			}
			//TODO - authorization chekc - Admin only can do insert
			pickupServcie.createPickup(userId, pickupDetails);
			
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);

		}
		
	}

}
