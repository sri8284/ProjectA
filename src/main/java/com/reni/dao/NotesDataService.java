package com.reni.dao;

import java.util.List;

import com.reni.model.Notes;
import com.reni.service.exception.RENIDataServiceException;

public interface NotesDataService {

	List<Notes> fetchNotes(Notes notes) throws RENIDataServiceException;

	void createNotes(Integer userId, Notes notes) throws RENIDataServiceException;

}
