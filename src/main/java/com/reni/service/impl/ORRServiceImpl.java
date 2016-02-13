package com.reni.service.impl;

import static com.reni.service.constants.RENIServiceConstant.DATA_FECTH_ERROR;
import static com.reni.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.ORRDataService;
import com.reni.model.OnRoadResource;
import com.reni.service.ORRService;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ORRServiceImpl implements ORRService {

	@Autowired
	ORRDataService orrDataService;

	@Override
	public List<OnRoadResource> fetchORRDetails() throws RENIServiceException {
		try {

			return orrDataService.fetchORRDetails();

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
	}

	@Override
	public List<OnRoadResource> fetchActiveORRDetails() throws RENIServiceException {
		try {

			return orrDataService.fetchActiveORRDetails();

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
		
	}

	@Override
	public void createORR(OnRoadResource onRoadResource) throws RENIServiceException {
		try {

			//TODO - validations
			orrDataService.createORR(onRoadResource);

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}

	}

	@Override
	public void updateORR(OnRoadResource onRoadResource) throws RENIServiceException {
		try {
			//TODO - validations
			 orrDataService.updateORR(onRoadResource);

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}

	}

	@Override
	public List<OnRoadResource> fetchOnHireORRDetails() throws RENIServiceException {
		try {

			return orrDataService.fetchOnHireORRDetails();

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
	}

	@Override
	public void createOnHireORR(OnRoadResource onRoadResource) throws RENIServiceException {
		try {
			//TODO - validations

			orrDataService.createOnHireORR(onRoadResource);

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}

	}

	@Override
	public List<OnRoadResource> fetchActiveOnHireORRDetails() throws RENIServiceException {
		try {

			return orrDataService.fetchActiveOnHireORRDetails();

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_FECTH_ERROR, DATA_FECTH_ERROR);
		}
	}

	@Override
	public void updateOnHireORR(OnRoadResource onRoadResource) throws RENIServiceException {
		try {
			//TODO - validations

			 orrDataService.updateOnHireORR(onRoadResource);

		} catch (RENIDataServiceException e) {
			throw new RENIServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}

	}

}
