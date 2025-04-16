package com.cg.hms.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.cg.hms.entities.Procedures;

@Service
public interface ProcedureService {
	
	public Procedures saveProcedure(Procedures procedure);
	public List<Procedures> getAllProcedures();
	public Procedures getCostOfProcedureById(Integer id);
	public Procedures getCostOfProcedureByName(String name);
	public Procedures updateCostOfProcedure(Integer procedureId,float cost);
	public Procedures updateNameOfProcedure(Integer procedureId,String name);

}
