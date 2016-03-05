package com.rnei.rowmapper;

import static com.rnei.service.constants.RENIDataConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.rnei.model.Notes;
import com.rnei.model.OnRoadResource;

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
