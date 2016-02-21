package com.reni.rowmapper;

import static com.reni.data.constants.RENIDataConstants.*;
import static com.reni.data.constants.RENIDataConstants.ASSIGNMENT_TYPE;
import static com.reni.data.constants.RENIDataConstants.CONCAT_NO;
import static com.reni.data.constants.RENIDataConstants.CREATED_BY;
import static com.reni.data.constants.RENIDataConstants.CREATED_DATE;
import static com.reni.data.constants.RENIDataConstants.CURRENT_DISPACT_ID;
import static com.reni.data.constants.RENIDataConstants.CURRENT_PICKUP_ID;
import static com.reni.data.constants.RENIDataConstants.DRIVING_LIC_NO;
import static com.reni.data.constants.RENIDataConstants.ORR_ID;
import static com.reni.data.constants.RENIDataConstants.ORR_NAME;
import static com.reni.data.constants.RENIDataConstants.PREV_PICKUP_ID;
import static com.reni.data.constants.RENIDataConstants.UPDATED_BY;
import static com.reni.data.constants.RENIDataConstants.UPDATED_DATE;
import static com.reni.data.constants.RENIDataConstants.VEHICLE_NO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.reni.model.Notes;
import com.reni.model.OnRoadResource;

public class NotesRowMapper implements RowMapper<Notes> {

	@Override
	public Notes mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Notes note = new Notes();

		note.setComments(rs.getString(NOTE_COMMENTS));
		note.setNoteDate(rs.getDate(NOTE_DATE));
		note.setNoteId(rs.getString(NOTE_ID));
		note.setCreatedBy(rs.getString(CREATED_BY));
		note.setCreatedDt(rs.getDate(CREATED_DATE));
		
		return note;
	}
}
