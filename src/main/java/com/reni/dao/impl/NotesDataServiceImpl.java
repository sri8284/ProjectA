package com.reni.dao.impl;

import static com.reni.common.util.CommonUtil.currentTimeStamp;
import static com.reni.data.constants.RENIDataConstants.*;
import static com.reni.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reni.dao.MasterDataRepository;
import com.reni.dao.NotesDataService;
import com.reni.model.Area;
import com.reni.model.ExpenseType;
import com.reni.model.Item;
import com.reni.model.Notes;
import com.reni.model.PaymentMode;
import com.reni.model.Vendor;
import com.reni.rowmapper.AreaRowMapper;
import com.reni.rowmapper.ExpenseTypeRowMapper;
import com.reni.rowmapper.ItemRowMapper;
import com.reni.rowmapper.NotesRowMapper;
import com.reni.rowmapper.PaymentModeRowMapper;
import com.reni.rowmapper.VendorRowMapper;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class NotesDataServiceImpl implements NotesDataService {

	private static final String SELECT_NOTES ="SELECT * FROM NOTES WHERE NOTE_ID=:NOTE_ID AND (NOTE_DATE >=:FROM_DATE AND NOTE_DATE < :TO_DATE ) " ;
	private static final String CREATE_NOTES =" INSERT INTO NOTES (NOTE_ID, NOTE_DATE, COMMENTS, CREATED_DATE, CREATED_BY) VALUES "
			+ " (:NOTE_ID, :NOTE_DATE,:COMMENTS, :CREATED_DATE, :CREATED_BY  ) " ;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public List<Notes> fetchNotes(Notes notes) throws RENIDataServiceException {
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		
		namedParameters.put(FROM_DATE, notes.getFromDate());
		namedParameters.put(TO_DATE, notes.getToDate());
		namedParameters.put(NOTE_ID, notes.getNoteId());
		
		return (List<Notes>) namedParameterJdbcTemplate.query(SELECT_NOTES, namedParameters, new NotesRowMapper());

	}



	@Override
	public void createNotes(Integer userId, Notes notes) throws RENIDataServiceException {

		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put(NOTE_DATE, notes.getNoteDate());
			namedParameters.put(NOTE_COMMENTS, notes.getComments());
			namedParameters.put(NOTE_ID, notes.getNoteId());
			namedParameters.put(CREATED_BY, userId);
			namedParameters.put(CREATED_DATE, currentTimeStamp());
		
			namedParameterJdbcTemplate.update(CREATE_NOTES, namedParameters);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RENIDataServiceException(RENIErrorCodes.DATA_SAVE_ERROR, DATA_SAVE_ERROR);
		}
	}

}
