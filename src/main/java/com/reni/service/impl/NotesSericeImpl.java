package com.reni.service.impl;

import static com.reni.service.constants.RENIServiceConstant.DATA_FECTH_ERROR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.reni.service.MasterDataSerice;
import com.reni.service.NotesSerice;
import com.reni.service.constants.RENIErrorCodes;
import com.reni.service.exception.RENIDataServiceException;
import com.reni.service.exception.RENIServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NotesSericeImpl implements NotesSerice {

	@Autowired
	NotesDataService notesDataSerice;

	@Override
	public List<Notes> fetchNotes(Notes notes) throws RENIServiceException {
		try{
		if(notes.getFromDate()==null || notes.getToDate()==null){
			throw new RENIServiceException("Notes fromDate and endDate is mandatory ");
		}
		return notesDataSerice.fetchNotes(notes);
		}catch(RENIDataServiceException e){
			e.printStackTrace();
			throw new RENIServiceException("Fetch error");
		}
	}

	@Override
	public void createNotes(Integer userId, Notes notes) throws RENIServiceException {
		try{
		if(notes.getComments()==null || notes.getNoteId()==null){
			throw new RENIServiceException("Comments and noteId is mandatory.");
		}
		
		notesDataSerice.createNotes(userId, notes);
		}catch(RENIDataServiceException e){
			e.printStackTrace();
			throw new RENIServiceException("Save Error ");
		}
	}

	


}
