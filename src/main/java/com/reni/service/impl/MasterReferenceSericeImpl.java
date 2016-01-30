package com.reni.service.impl;

import static com.reni.service.constants.RENIServiceConstant.DATA_FECTH_ERROR;
import static com.reni.service.constants.RENIServiceConstant.INVALID_ACCESSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.UserDataService;
import com.reni.model.PickupRefernce;
import com.reni.service.MasterDataService;
import com.reni.service.MasterReferenceSerice;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;
import com.reni.service.exception.RENIValidationException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MasterReferenceSericeImpl implements MasterReferenceSerice {

	@Autowired
	UserDataService userDataService;
	@Autowired
	MasterDataService masterDataService;

	@Override
	public PickupRefernce getPickupReferenceDetails(Integer userId,String sessionId) throws RENIServiceException {
		try {
			if (!userDataService.isSessionValid(userId,sessionId)) {
				throw new RENIValidationException(RENIErrorCodes.INVALID_ACCESSS, INVALID_ACCESSS);
			}
			
		final PickupRefernce pickupRefeence = new PickupRefernce();

		pickupRefeence.setAreas(masterDataService.getAreas());
		pickupRefeence.setItems(masterDataService.getItems());
		pickupRefeence.setOrrs(masterDataService.getORRDetails());
		pickupRefeence.setVendors(masterDataService.getVendors());
		
		return pickupRefeence;	
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
	}

}
