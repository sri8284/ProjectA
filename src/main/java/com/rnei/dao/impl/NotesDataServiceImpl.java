package com.rnei.dao.impl;

import static com.rnei.common.util.CommonUtil.currentTimeStamp;
import static com.rnei.service.constants.RENIDataConstants.CREATED_BY;
import static com.rnei.service.constants.RENIDataConstants.CREATED_DATE;
import static com.rnei.service.constants.RENIDataConstants.FROM_DATE;
import static com.rnei.service.constants.RENIDataConstants.NOTE_COMMENTS;
import static com.rnei.service.constants.RENIDataConstants.NOTE_DATE;
import static com.rnei.service.constants.RENIDataConstants.NOTE_ID;
import static com.rnei.service.constants.RENIDataConstants.TO_DATE;
import static com.rnei.service.constants.RENIServiceConstant.DATA_SAVE_ERROR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.NotesDataService;
import com.rnei.model.Notes;
import com.rnei.rowmapper.NotesRowMapper;
import com.rnei.service.exception.RENIDataServiceException;

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
			throw new RENIDataServiceException( DATA_SAVE_ERROR, e);
		}
	}

}
