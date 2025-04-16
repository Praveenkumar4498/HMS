package com.cg.hms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.hms.entities.Physician;

@Service
public interface PhysicianService {
	
	
	public Physician savePhysician(Physician physician);
	public Physician getPhysicianDetailsByName(String name);
	public List<Physician>  getallPhysiciansByPositions(String position);
	public Physician  getPhysicianByEmpid(Integer employeeId);
	public Physician updatePositionOfPhysician(Integer employeeId,String position);
	public Physician updateNameOfPhysician(Integer employeeId,String name);
	public Physician updateSSNOfPhysician(Integer employeeId,Integer ssn);

	
	
	
	

}
