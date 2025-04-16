package com.cg.hms.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hms.entities.AffiliatedWith;
import com.cg.hms.entities.Department;
import com.cg.hms.entities.Physician;
import com.cg.hms.services.AffiliatedServiceImpl;

 


@RestController
@RequestMapping(value="/api/affiliated_with")
public class AffiliatedWithController{
 private AffiliatedServiceImpl service;
 @Autowired
 public void setService(AffiliatedServiceImpl service) {
	 this.service = service;
 }
@PostMapping(value="/post")
    public ResponseEntity<String> saveAffiliatedwith(@RequestParam("physicianId") int physicianId) {
	  service.saveAffiliatedwith(physicianId);
      return new ResponseEntity<>("Successfully Created",HttpStatus.CREATED);
}
@GetMapping(value="/physicians/{departmentId}")
     public ResponseEntity <List<Physician>> getPhysiciansByDepartment(@PathVariable int departmentId) {
	 List<Physician> physicians = service.getPhysiciansByDepartment(departmentId);
     return new ResponseEntity<>(physicians,HttpStatus.OK);
}
@GetMapping(value="/department/{physicianId}")
    public ResponseEntity<List<Department>> getDepartmentsByPhysician(@PathVariable int physicianId) {
	  List<Department> departments = service.getDepartmentsByPhysician(physicianId);
      return new ResponseEntity<>(departments,HttpStatus.OK);
}
@GetMapping(value="/countphysician/{departmentId}")
    public ResponseEntity<Integer> countPhysiciansByDepartment(@PathVariable int departmentId) {
      Integer count = service.countPhysiciansByDepartment(departmentId);
      return new ResponseEntity<>(count,HttpStatus.OK);
}
@GetMapping(value="/{physicianId}")
    public ResponseEntity<Boolean> getPrimaryAffiliation(@PathVariable int physicianId) {
    Boolean affiliation = service.getPrimaryAffiliation(physicianId); 
	return new ResponseEntity<>(affiliation,HttpStatus.OK);
}
@PutMapping(value="/primary")
    public ResponseEntity <Boolean> updatePrimaryAffiliation(@RequestParam("pAffiliation") Boolean pAffiliation,@RequestParam("physicianId")int physicianId) {
    Boolean pAffiliation1 = service.updatePrimaryAffiliation(physicianId,pAffiliation);
	return new ResponseEntity<>(pAffiliation1,HttpStatus.OK);
  }
}