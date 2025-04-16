package com.cg.hms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hms.entities.Physician;
import com.cg.hms.exceptions.DuplicateEntryException;
import com.cg.hms.exceptions.NoRecordsException;
import com.cg.hms.exceptions.NoSuchElementException;
import com.cg.hms.repositories.PhysicianRepository;

@Service
public class PhysicianServiceImpl implements PhysicianService  {
	private final String NOSUCH = "No physician record available with that id : ";
	
	private PhysicianRepository physicianRepository;
	
	@Autowired
	public void setPhysicianRepository(PhysicianRepository physicianRepository) {
		this.physicianRepository = physicianRepository;
	}

	@Override
	public Physician savePhysician(Physician physician) {
		if(physicianRepository.findById(physician.getEmployeeId()).isEmpty())
		  return physicianRepository.save(physician);
		throw new DuplicateEntryException("Trying to insert duplicate record");
	}

	@Override
	public Physician getPhysicianDetailsByName(String name) {
		
		return physicianRepository.findByName(name).orElseThrow(()-> new NoSuchElementException("No physician record available with that name"));
	}

	@Override
	public List<Physician> getallPhysiciansByPositions(String position) {
		
		List<Physician> physicians = physicianRepository.findByPosition(position);
		if(physicians.isEmpty())
		 throw new NoRecordsException("No records available in the table");
		return physicians;
	}

	@Override
	public Physician getPhysicianByEmpid(Integer employeeId) {
		
		return physicianRepository.findById(employeeId).orElseThrow(()-> new NoSuchElementException(NOSUCH));
	}

	@Override
	public Physician updatePositionOfPhysician(Integer employeeId,String position) {
		Physician obj=physicianRepository.findById(employeeId).orElseThrow(() -> new NoSuchElementException(NOSUCH + employeeId));
		obj.setPosition(position);
		return physicianRepository.save(obj);
		
	}

	@Override
	public Physician updateNameOfPhysician(Integer employeeId,String name) {
		Physician physician1=physicianRepository.findById(employeeId).orElseThrow(() -> new NoSuchElementException(NOSUCH+ employeeId));
		physician1.setName(name);
		return physicianRepository.save(physician1);
		
	}

	@Override
	public Physician updateSSNOfPhysician(Integer employeeId, Integer ssn) {
		Physician physician1=physicianRepository.findById(employeeId).orElseThrow(() -> new NoSuchElementException(NOSUCH+ employeeId));
		physician1.setSsn(ssn);
		return physicianRepository.save(physician1);
	}
	
	

}
