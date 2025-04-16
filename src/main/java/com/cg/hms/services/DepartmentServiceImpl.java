package com.cg.hms.services;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hms.entities.Department;
import com.cg.hms.entities.Physician;
import com.cg.hms.entities.TrainedIn;
import com.cg.hms.exceptions.DuplicateEntryException;
import com.cg.hms.exceptions.NoEntryException;
import com.cg.hms.exceptions.NoRecordsException;
import com.cg.hms.exceptions.NoSuchElementException;
import com.cg.hms.repositories.DepartmentRepository;
import com.cg.hms.repositories.TrainedInRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	private static final String NOSUCH = "No department available for given id";
	private DepartmentRepository departmentRepository ;
	private TrainedInRepository trainedInRepository ;
	
	
   @Autowired
	public void setServices(DepartmentRepository departmentRepository,TrainedInRepository trainedInRepository) {
		this.departmentRepository = departmentRepository;
		this.trainedInRepository = trainedInRepository;
	
	}

	@Override
	//1
	public Department saveDepartment(Department department) {
		if(departmentRepository.findById(department.getDepartmentId()).isEmpty())
			return departmentRepository.save(department);
		throw new DuplicateEntryException("Department record with the given id is already existed");
		
	}

	@Override
	//2
	public List<Department> getallDepartments() {
		
		List<Department> departments = departmentRepository.findAll();
		if(departments.isEmpty())
		  throw new NoRecordsException("No records found in the table");
		return departments;
	}

	@Override
	//3
	public Department getDepartmentDetailByDeptId(Integer departmentId) {
		
		return departmentRepository.findById(departmentId).orElseThrow(()-> new NoSuchElementException(NOSUCH+departmentId));
	}

	@Override
	//4
	public Physician getHeadOfDepartmentDetails(Integer physicianId) {
		return departmentRepository.findbyhe(physicianId).orElseThrow(()-> new NoSuchElementException(NOSUCH));
	}

	@Override
	  public List<TrainedIn> getHeadCertificationDetailByDeptId(Integer departmentid)
	  { 
	  Physician physician = departmentRepository.findbyhe(departmentid).orElseThrow(()-> new NoSuchElementException(NOSUCH));
	  List<TrainedIn> certifications=trainedInRepository.findByPhysician(physician);
	  if(certifications.isEmpty())
		  throw new NoEntryException("No certifications found for given physician");
	  return certifications; 
	  }
	@Override
	//6
	public List<Department> getDepartmentByHeadId(Integer head) {

		List<Department> departments =  departmentRepository.findByHead(head);
		if(departments.isEmpty())
			throw new NoRecordsException("No records available in the table");
		return departments;
	}

	@Override
	//7
	public Boolean physicianIsHeadOfAnyDepartmentOrNot(Integer physicianId) {
		List<Department> list = getDepartmentByHeadId(physicianId);
		if(list.isEmpty())
		  throw new NoEntryException("given physician is not head to any department");
		return true;
	}

	@Override
	//8
	public Department updateDepartmentHeadId(Physician physician, Integer departmentId) {
		Department department1=departmentRepository.findById(departmentId).orElseThrow(()->new NoSuchElementException(NOSUCH));
		department1.setHead(physician);
		return departmentRepository.save(department1);
		
	}

	@Override
	//9
	public Department updateNameOfDepartment(Integer departmentId, String name) {
		Department department=departmentRepository.findById(departmentId).orElseThrow(()->new NoSuchElementException(NOSUCH));
		department.setName(name);
		return departmentRepository.save(department);
		
		
	}

	

}
