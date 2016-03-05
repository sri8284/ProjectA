package com.rnei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.NotesDataService;
import com.rnei.model.Notes;
import com.rnei.service.NotesSerice;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NotesSericeImpl implements NotesSerice {

	@Autowired
	NotesDataService notesDataSerice;

	@Override
	public List<Notes> fetchNotes(Notes notes) throws RENIServiceException {
		if(notes.getFromDate()==null || notes.getToDate()==null){
			throw new RENIValidationException("Notes fromDate and endDate is mandatory ");
		}
		return notesDataSerice.fetchNotes(notes);
	}

	@Override
	public void createNotes(Integer userId, Notes notes) throws RENIServiceException {
		if(notes.getComments()==null || notes.getNoteId()==null){
			throw new RENIValidationException("Comments and noteId is mandatory.");
		}
		
		notesDataSerice.createNotes(userId, notes);
	}

	


}
