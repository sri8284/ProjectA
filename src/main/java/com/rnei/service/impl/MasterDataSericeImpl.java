package com.rnei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.MasterDataRepository;
import com.rnei.model.Area;
import com.rnei.model.ExpenseType;
import com.rnei.model.Item;
import com.rnei.model.PaymentMode;
import com.rnei.model.Vendor;
import com.rnei.service.MasterDataSerice;
import com.rnei.service.exception.RENIServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MasterDataSericeImpl implements MasterDataSerice {

	@Autowired
	MasterDataRepository repository;

	@Override
	public List<Vendor> fetchVendorDetails() throws RENIServiceException {
			return repository.fetchVendorDetails();
	}

	@Override
	public List<Item> fetchItemDetails() throws RENIServiceException {
			return repository.fetchItemDetails();
	}

	@Override
	public List<Area> fetchAreaDetails() throws RENIServiceException {
			return repository.fetchAreaDetails();
	}

	@Override
	public List<ExpenseType> fetchExpenseTypeDetails() throws RENIServiceException {
			return repository.fetchExpenseTypeDetails();
	}

	@Override
	public List<PaymentMode> fetchPaymentModes() throws RENIServiceException {
			return repository.fetchPaymentModes();
	}

	


}
