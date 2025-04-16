package com.cg.hms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hms.entities.Procedures;
import com.cg.hms.exceptions.DuplicateEntryException;
import com.cg.hms.exceptions.NoRecordsException;
import com.cg.hms.exceptions.NoSuchElementException;
import com.cg.hms.repositories.ProcedureRepository;

@Service
public class ProcedureServiceImpl implements ProcedureService{
	
	private static final String NOSUCH = "No procedure available with the given id";
	private ProcedureRepository procedureRepository;
	
	@Autowired
	public void setProcedureService( ProcedureRepository procedureRepository){
		this.procedureRepository=procedureRepository;
	}

	@Override
	public Procedures saveProcedure(Procedures procedure) {
	if(procedureRepository.findById(procedure.getCode()).isEmpty())
		return procedureRepository.save(procedure);
	throw new DuplicateEntryException("Procedure with the given id is already existed");
	
		
	}

	@Override
	public List<Procedures> getAllProcedures() {
		
		List<Procedures> procedures = procedureRepository.findAll();
		if(procedures.isEmpty())
		  throw new NoRecordsException("No records found in the table");
		return procedures;
	}

	@Override
	public Procedures getCostOfProcedureById(Integer id) {
		return procedureRepository.findById(id).orElseThrow(()-> new NoSuchElementException(NOSUCH));
		
	}

	@Override
	public Procedures getCostOfProcedureByName(String name) {
		return procedureRepository.findByName(name).orElseThrow(()-> new NoSuchElementException(NOSUCH));
	}

	@Override
	public Procedures updateCostOfProcedure(Integer id,float cost) {
		Procedures procedure=procedureRepository.findById(id).orElseThrow(()-> new NoSuchElementException(NOSUCH));
		procedure.setCost(cost);
	    return procedureRepository.save(procedure);
	}

	@Override
	public Procedures updateNameOfProcedure(Integer procedureId,String name) {
		Procedures procedure=procedureRepository.findById(procedureId).orElseThrow(()-> new NoSuchElementException(NOSUCH));
	    procedure.setName(name);
	    return procedureRepository.save(procedure);
	}

}
