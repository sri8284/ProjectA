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
import com.reni.model.ItemTransaction;
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
	PickupDataService pickupDataServcie;
	
	@Override
	public Pickup fetchPickupDetailsById(String pickupId) throws RENIServiceException {
		try {
			
			return pickupDataServcie.getPickup(pickupId);
			
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
		
	}

	@Override
	public void createPickup(Integer userId,Pickup pickupDetails) throws RENIServiceException {
		try {
			//TODO - mandatory field validations
			validateCreatePickupMandatoryFields(pickupDetails, NEW_PICKUP);

			pickupDetails.setPickupId(String.valueOf(System.currentTimeMillis()));
			
			pickupDataServcie.createPickup(userId, pickupDetails);
			
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);

		}
		
	}

	private void validateCreatePickupMandatoryFields(Pickup pickupDetails, String assignmentType) throws RENIServiceException {
		if(pickupDetails.getOrrId()==null){
			throw new RENIValidationException("ORR ID is mandatory for creating pickup.");
		}
		if(pickupDetails.getItemTransactions()==null){
			throw new RENIValidationException("At least one item is required for creating pickup.");

		}
		for(ItemTransaction items: pickupDetails.getItemTransactions()){
			if(items.getItemExpectedVol()==null){
				throw new RENIValidationException("One of item expected volumn is not provided.");

			}
		}
		
	}

	@Override
	public List<Map<String, Object>> fetchPickupsReport(String pickupDateInput) throws RENIServiceException {
		
		try {
			LocalDate pickupDate = LocalDate.parse(pickupDateInput);

			return pickupDataServcie.fetchPickupsReport(pickupDate);
			
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);

		}
		
	}

	@Override
	public List<Map<String, Object>> fetchPickupsAssignmentStatus(String pickupDateInput) throws RENIServiceException {
		try {
			
			LocalDate pickupDate = LocalDate.parse(pickupDateInput);
			
			return pickupDataServcie.fetchPickupsAssignmentStatus(pickupDate);
			
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);

		}
	}

	@Override
	public List<Map<String, Object>> fetchPickupStatus(String pickupDateInput) throws RENIServiceException {
		try {
			LocalDate pickupDate = LocalDate.parse(pickupDateInput);
			
			return pickupDataServcie.fetchPickupStatus(pickupDate);
			
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);

		}
	}

	@Override
	public void closePickup(Integer userId, String pickupId, Pickup pickupInput) throws RENIServiceException {
		try {
			//TODO - mandatory field validations
			if(pickupId==null){
				throw new RENIValidationException("Pickup ID is mandatory for close the pickup.");

			}
			//TODO - check PickupID is exsited or not.
			
			pickupInput.setPickupId(pickupId);
			
			validatePayment(pickupInput);
			
			if(pickupInput.getTotalPayment() == pickupInput.getBalancePayment()+pickupInput.getParitalPayment()){
				pickupInput.setAssignmentType(CLOSE_PICKUP);
				pickupInput.setComplete(COMPLETED);
			}
			pickupInput.setAssignmentType(PENDING_FOR_SETTLEMENT);
			pickupInput.setComplete(NON_COMPLETE);
			
			pickupDataServcie.closePickup(userId, pickupInput);
			
		} catch (RENIDataServiceException e) {
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);

		}

}

	private void validatePayment(Pickup pickupInput) throws RENIValidationException {
		Float itemTotalAmount = 0F;
		if(pickupInput.getTotalPayment()!=(pickupInput.getParitalPayment() + pickupInput.getBalancePayment())){
			throw new RENIValidationException("Total Payment is not mached with Parital+Balance Payment");
	
		}
		
		for(ItemTransaction itemTransaction : pickupInput.getItemTransactions()){
			Float itemAmount=itemTransaction.getItemActualVol()*itemTransaction.getItemPaidRate();
			itemTotalAmount = itemTotalAmount+itemAmount;
		}
		if(pickupInput.getTotalPayment()!=itemTotalAmount.floatValue()){
			throw new RENIValidationException("The Pickup Total Amount is not mached with Total items amount.");
		}
		
	}
}
