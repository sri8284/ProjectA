package com.rnei.service;

import java.util.List;

import com.rnei.model.Notes;
import com.rnei.service.exception.RENIServiceException;

public interface NotesSerice {

	List<Notes> fetchNotes(Notes notes) throws RENIServiceException;

	void createNotes(Integer userId, Notes notes) throws RENIServiceException;

}
