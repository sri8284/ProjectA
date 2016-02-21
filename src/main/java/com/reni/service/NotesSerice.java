package com.reni.service;

import java.util.List;

import com.reni.model.Notes;
import com.reni.service.exception.RENIServiceException;

public interface NotesSerice {

	List<Notes> fetchNotes(Notes notes) throws RENIServiceException;

	void createNotes(Integer userId, Notes notes) throws RENIServiceException;

}
