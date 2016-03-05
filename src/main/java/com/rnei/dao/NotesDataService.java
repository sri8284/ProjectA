package com.rnei.dao;

import java.util.List;

import com.rnei.model.Notes;
import com.rnei.service.exception.RENIDataServiceException;

public interface NotesDataService {

	List<Notes> fetchNotes(Notes notes) throws RENIDataServiceException;

	void createNotes(Integer userId, Notes notes) throws RENIDataServiceException;

}
