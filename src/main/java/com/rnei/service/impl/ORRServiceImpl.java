package com.rnei.service.impl;

import static com.rnei.common.util.CommonUtil.isNullOrEmpty;
import static com.rnei.service.constants.RENIServiceConstant.DUPLICATE_ONHIRE_ORR;
import static com.rnei.service.constants.RENIServiceConstant.ONHIRE_ORR_MANADATORY;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.ORRDataService;
import com.rnei.model.OnRoadResource;
import com.rnei.model.OnRoadResourcePickup;
import com.rnei.model.Pickup;
import com.rnei.service.ORRService;
import com.rnei.service.exception.RENIDataServiceException;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ORRServiceImpl implements ORRService {

	@Autowired
	ORRDataService orrDataService;

	@Override
	public List<OnRoadResource> fetchORRDetails() throws RENIServiceException {
			return orrDataService.fetchORRDetails();
	}

	@Override
	public List<OnRoadResource> fetchActiveORRDetails() throws RENIServiceException {
			return orrDataService.fetchActiveORRDetails();
	}

	@Override
	public void createORR(OnRoadResource onRoadResource) throws RENIServiceException {
			orrDataService.createORR(onRoadResource);
	}

	@Override
	public void updateORR(OnRoadResource onRoadResource) throws RENIServiceException {
			 orrDataService.updateORR(onRoadResource);
	}

	@Override
	public List<OnRoadResource> fetchOnHireORRDetails() throws RENIServiceException {
			return orrDataService.fetchOnHireORRDetails();
	}

	@Override
	public String createOnHireORR(String userId,OnRoadResource onRoadResource) throws RENIServiceException {
			if(isNullOrEmpty(onRoadResource.getConcatNo(),onRoadResource.getDrivingLicNo(),onRoadResource.getOrrName(),onRoadResource.getVehicleNo())){
				throw new RENIValidationException(ONHIRE_ORR_MANADATORY);
			}
			
			if(orrDataService.isDuplicateOnHireORR(onRoadResource.getDrivingLicNo())){
				throw new RENIValidationException(DUPLICATE_ONHIRE_ORR);
			}
			
			return orrDataService.createOnHireORR(userId,onRoadResource);
	}

	@Override
	public List<OnRoadResource> fetchActiveOnHireORRDetails() throws RENIServiceException {
			return orrDataService.fetchActiveOnHireORRDetails();
	}

	@Override
	public void updateOnHireORR(String userId, OnRoadResource onRoadResource) throws RENIServiceException {
			 orrDataService.updateOnHireORR(userId, onRoadResource);
	}

	@Override
	public Object fetchORRReportDetails(String orrId, String reqDate) {
		//TODO - validate the orrID is valid or not - should be active
		//TODO - convert the String to Date.
		//TODO - fetch the his total pickups on requested date in pickup table. join with orr table that is not allocated to any pickup.
		return null;
	}

	@Override
	public Object fetchOnHireORRReportDetails(String orrId, String reqDate) {
		//TODO - validate the orrID is valid or not - should be active
		
		return null;
	}

	@Override
	public List<Pickup> fetchORRPickupDetails(String orrId, LocalDate pickupDate) throws RENIValidationException {
		if(orrId ==null){
			throw new RENIValidationException("ORR ID is mandatory");
		}
		if(pickupDate==null){
			throw new RENIValidationException("Date is mandatory");
		}
		
		return orrDataService.fetchORRPickupDetails(orrId, pickupDate);
	}

	@Override
	public OnRoadResource fetchORRDetailsByID(String orrId) throws RENIDataServiceException {
		return orrDataService.getOrrDetails(orrId);
	}

	@Override
	public OnRoadResource fetchOnHireORRDetailsByID(String orrId) throws RENIDataServiceException {
		return orrDataService.getOnhireOrrDetails(orrId);
	}

}
