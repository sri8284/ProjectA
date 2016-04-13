package com.rnei.service.impl;

import static com.rnei.service.constants.RENIServiceConstant.CLOSE_PICKUP;
import static com.rnei.service.constants.RENIServiceConstant.COMPLETED;
import static com.rnei.service.constants.RENIServiceConstant.NEW_PICKUP;
import static com.rnei.service.constants.RENIServiceConstant.NON_COMPLETE;
import static com.rnei.service.constants.RENIServiceConstant.PENDING_FOR_SETTLEMENT;

import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.PickupDataService;
import com.rnei.dao.UserDataService;
import com.rnei.model.ItemTransaction;
import com.rnei.model.Pickup;
import com.rnei.service.PickupService;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class PickupServiceImpl implements PickupService {

	@Autowired
	UserDataService userDataService;

	@Autowired
	PickupDataService pickupDataServcie;

	@Override
	public Pickup fetchPickupDetailsById(String pickupId) throws RENIServiceException {
		return pickupDataServcie.getPickup(pickupId);
	}

	@Override
	public String createPickup(String userId, Pickup pickupDetails) throws RENIServiceException {
		// TODO - mandatory field validations
		validateCreatePickupMandatoryFields(pickupDetails, NEW_PICKUP);
		String pickupId = String.valueOf(System.currentTimeMillis());
		pickupDetails.setPickupId(pickupId);
		pickupDataServcie.createPickup(userId, pickupDetails);
		return pickupId;
	}

	private void validateCreatePickupMandatoryFields(Pickup pickupDetails, String assignmentType)
			throws RENIServiceException {
		if (pickupDetails.getOrrId() == null) {
			throw new RENIValidationException("ORR ID is mandatory for creating pickup.");
		}
		if (pickupDetails.getItemTransactions() == null) {
			throw new RENIValidationException("At least one item is required for creating pickup.");

		}
		for (ItemTransaction items : pickupDetails.getItemTransactions()) {
			if (items.getItemExpectedVol() == null) {
				throw new RENIValidationException("One of item expected volumn is not provided.");
			}
		}
	}

	@Override
	public List<Map<String, Object>> fetchPickupsReport(String pickupDateInput) throws RENIServiceException {
		LocalDate pickupDate = LocalDate.parse(pickupDateInput);

		return pickupDataServcie.fetchPickupsReport(pickupDate);
	}

	@Override
	public List<Map<String, Object>> fetchPickupsAssignmentStatus(String pickupDateInput) throws RENIServiceException {
		LocalDate pickupDate = LocalDate.parse(pickupDateInput);

		return pickupDataServcie.fetchPickupsAssignmentStatus(pickupDate);
	}

	@Override
	public List<Map<String, Object>> fetchPickupStatus(String pickupDateInput) throws RENIServiceException {
		LocalDate pickupDate = LocalDate.parse(pickupDateInput);

		return pickupDataServcie.fetchPickupStatus(pickupDate);
	}

	@Override
	public void closePickup(String userId, String pickupId, Pickup pickupInput) throws RENIServiceException {
		// TODO - mandatory field validations
		if (pickupId == null) {
			throw new RENIValidationException("Pickup ID is mandatory for close the pickup.");

		}
		// TODO - check PickupID is exsited or not.
		pickupInput.setPickupId(pickupId);
		validatePayment(pickupInput);

		if( pickupInput.getParitalPayment() !=null && pickupInput.getBalancePayment() !=null &&  pickupInput.getParitalPayment()!=0 && pickupInput.getBalancePayment() !=0){
			pickupInput.setPickupStatus(PENDING_FOR_SETTLEMENT);
			pickupInput.setComplete(NON_COMPLETE);
		}else{
			pickupInput.setPickupStatus(CLOSE_PICKUP);
			pickupInput.setComplete(COMPLETED);
		}
		
		pickupDataServcie.closePickup(userId, pickupInput);
	}

	private void validatePayment(Pickup pickupInput) throws RENIValidationException {
		Float itemTotalAmount = 0F;
		if( pickupInput.getParitalPayment() !=null && pickupInput.getBalancePayment() !=null &&  pickupInput.getParitalPayment()!=0 && pickupInput.getBalancePayment() !=0){
			if (pickupInput.getTotalPayment() != (pickupInput.getParitalPayment() + pickupInput.getBalancePayment())) {
				throw new RENIValidationException("Total Payment is not mached with Parital+Balance Payment");
			}
		}
		
		for (ItemTransaction itemTransaction : pickupInput.getItemTransactions()) {
			Float itemAmount = itemTransaction.getItemActualVol() * itemTransaction.getItemPaidRate();
			itemTotalAmount = itemTotalAmount + itemAmount;
		}
		if (pickupInput.getTotalPayment() != itemTotalAmount.floatValue()) {
			throw new RENIValidationException("The Pickup Total Amount is not mached with Total items amount.");
		}

	}
}
