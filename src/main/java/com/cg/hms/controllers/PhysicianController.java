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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hms.entities.Physician;
import com.cg.hms.exceptions.ValidationFailedException;
import com.cg.hms.services.PhysicianServiceImpl;

@RestController
@RequestMapping(value="/api/physician")
public class PhysicianController {
private PhysicianServiceImpl service;
@Autowired
public void setService(PhysicianServiceImpl service) {
	this.service = service;
}
@PostMapping
public ResponseEntity<String> savePhysician(@Valid @RequestBody Physician physician,  BindingResult bindingResult){
	
	 if (bindingResult.hasErrors()) {
			throw new ValidationFailedException("validation failed");
			}
	service.savePhysician(physician);
	return new ResponseEntity<>("success",HttpStatus.OK);
}
@GetMapping
public ResponseEntity<Physician> getPhysicianDetailsByName(@RequestParam("name") String name){
	Physician physician = service.getPhysicianDetailsByName(name);
	return new ResponseEntity<>(physician,HttpStatus.OK);
}


  @GetMapping(value="/")
  public ResponseEntity<List<Physician>> getallPhysiciansByPositions(@RequestParam("position") String position){
  List<Physician> physicians = service.getallPhysiciansByPositions(position);
  return new ResponseEntity<>(physicians,HttpStatus.OK); }
 
@GetMapping(value ="/{employeeId}")
public ResponseEntity<Physician> getPhysicianByEmpid(@PathVariable Integer employeeId){
	Physician physician = service.getPhysicianByEmpid(employeeId);
	return new ResponseEntity<>(physician,HttpStatus.OK);
}

 @PutMapping(value = "/update/position") public
 ResponseEntity<Physician> updatePositionOfPhysician(@RequestParam("position") String position,@RequestParam("employeeId") Integer employeeId){
 Physician physician = service.updatePositionOfPhysician(employeeId, position);
 return new ResponseEntity<>(physician,HttpStatus.OK); 
 }
 @PutMapping(value = "/update/name") public
 ResponseEntity<Physician> updateNameOfPhysician(@RequestParam("name") String name,@RequestParam("employeeId") Integer employeeId){ 
 Physician physician = service.updateNameOfPhysician(employeeId,name);
 return new ResponseEntity<>(physician,HttpStatus.OK); 
 }
 @PutMapping(value = "/update/ssn") 
 public ResponseEntity<Physician> updateSSNOfPhysician(@RequestParam("ssn") Integer ssn,@RequestParam("employeeId") Integer employeeId){
 Physician obj = service.updateSSNOfPhysician(employeeId,ssn);
 return new ResponseEntity<>(obj,HttpStatus.OK); 
 }
 
 




}