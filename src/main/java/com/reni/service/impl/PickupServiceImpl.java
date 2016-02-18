package com.reni.service.impl;

import static com.reni.service.constants.RENIServiceConstant.*;
import static com.reni.service.constants.RENIServiceConstant.INVALID_ACCESSS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public Pickup fetchPickupDetailsById(String pickupId) throws RENIServiceException {
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
	public void createPickup(Integer userId,Pickup pickupDetails) throws RENIServiceException {
		try {
		
			pickupServcie.createPickup(userId, pickupDetails);
			
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);

		}
		
	}

	@Override
	public Map<String, Object> fetchCompletePickupsStatusDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> fetchPickupsStatus(LocalDate pickupDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> fetchPickupReportByDate(LocalDate pickupDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
