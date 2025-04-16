package com.cg.hms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hms.entities.Department;
import com.cg.hms.entities.Physician;
import com.cg.hms.entities.TrainedIn;
import com.cg.hms.exceptions.ValidationFailedException;
import com.cg.hms.services.DepartmentServiceImpl;

@RestController
@RequestMapping(value="/api/department")
public class DepartmentController {
private DepartmentServiceImpl service;
@Autowired
public void setService(DepartmentServiceImpl service) {
	this.service = service;
}
@PostMapping
public ResponseEntity<String> addDepart(@Valid @RequestBody Department dep, BindingResult bindingResult) {
	
	if (bindingResult.hasErrors()) {
	throw new ValidationFailedException("validation failed");
	}
	service.saveDepartment(dep);
    return new ResponseEntity<String>("success",HttpStatus.CREATED);
}
@GetMapping(value="/")
	public ResponseEntity<List<Department>> getallDepartments() {
	    List<Department> departments = service.getallDepartments();
		return new ResponseEntity<>(departments,HttpStatus.OK);
	}
@GetMapping(value="/{departmentId}")
	public ResponseEntity<Department> getDepartmentDetailByDeptId(@RequestParam(value="departmentId") Integer departmentId) {
	Department department = service.getDepartmentDetailByDeptId(departmentId);	
	return new ResponseEntity<>(department,HttpStatus.OK);
}
@GetMapping(value="/head/{physicianId}")
	public ResponseEntity<Physician> getHeadOfDepartmentDetails(@PathVariable int physicianId) {
	
        Physician physician = service.getHeadOfDepartmentDetails(physicianId);
		return new ResponseEntity<>(physician,HttpStatus.OK);
}
@GetMapping(value="/headcertification/{departmentId}")
	public ResponseEntity<List<TrainedIn>> getHeadCertificationDetailByDeptId(@PathVariable Integer departmentId) {
		List<TrainedIn> list = service.getHeadCertificationDetailByDeptId(departmentId);
	    return new ResponseEntity<>(list,HttpStatus.OK);
}
@PutMapping(value="/headId")
	public ResponseEntity<List<Department>> getDepartmentByHeadId(@RequestParam(value="headId") int headId) {
		List<Department> departments = service.getDepartmentByHeadId(headId);
		return  new ResponseEntity<>(departments,HttpStatus.OK);
}
@GetMapping(value="/check/{physicianId}")
	public ResponseEntity<Boolean> physicianIsHeadOfAnyDepartmentOrNot(@PathVariable Integer physicianId ) {
		Boolean ishead = service.physicianIsHeadOfAnyDepartmentOrNot(physicianId);
		return new ResponseEntity<>(ishead,HttpStatus.OK);
}
@PutMapping(value="/update/headid")
public ResponseEntity<Department> updateDepartmentHeadId(@RequestBody Physician physician,@RequestParam("departmentId") Integer departmentId) {
    Department dept = service.updateDepartmentHeadId(physician,departmentId);
	return new ResponseEntity<>(dept,HttpStatus.OK);
}
@PutMapping(value="/update/deptname")
public ResponseEntity<Department> updateNameOfDepartment(@RequestParam("name") String name,@RequestParam("departmentId") Integer departmentId) {
    Department department = service.updateNameOfDepartment(departmentId,name);
	return new ResponseEntity<>(department,HttpStatus.OK);
}
}
