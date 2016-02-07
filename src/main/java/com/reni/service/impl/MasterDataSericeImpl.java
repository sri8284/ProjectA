package com.reni.service.impl;

import static com.reni.service.constants.RENIServiceConstant.DATA_FECTH_ERROR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.MasterDataRepository;
import com.reni.model.Area;
import com.reni.model.ExpenseType;
import com.reni.model.Item;
import com.reni.model.Vendor;
import com.reni.service.MasterDataSerice;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MasterDataSericeImpl implements MasterDataSerice {

	@Autowired
	MasterDataRepository repository;

	@Override
	public List<Vendor> fetchVendorDetails() throws RENIServiceException {
		try{
			return repository.fetchVendorDetails();
			
		}catch(RENIDataServiceException e){
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
	}

	@Override
	public List<Item> fetchItemDetails() throws RENIServiceException {
		try{
			return repository.fetchItemDetails();
			
		}catch(RENIDataServiceException e){
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
	}

	@Override
	public List<Area> fetchAreaDetails() throws RENIServiceException {
		try{
			return repository.fetchAreaDetails();
			
		}catch(RENIDataServiceException e){
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
	}

	@Override
	public List<ExpenseType> fetchExpenseTypeDetails() throws RENIServiceException {
		try{
			return repository.fetchExpenseTypeDetails();
			
		}catch(RENIDataServiceException e){
			e.printStackTrace();
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
	}

	


}
