
  package com.cg.hms.controllers;
  
  import java.util.List;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.PathVariable; import
  org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RequestMethod; import
  org.springframework.web.bind.annotation.RequestParam; import
  org.springframework.web.bind.annotation.RestController;

import com.cg.hms.entities.Nurse;
import com.cg.hms.entities.Patient;
import com.cg.hms.exceptions.ValidationFailedException;
import com.cg.hms.services.PatientServiceImpl;
  
  @RestController
  
  @RequestMapping(value="/api/patient") public class PatientController {
  private PatientServiceImpl service;
  
  @Autowired public void setService(PatientServiceImpl service) { this.service
  = service; }
  
  @PostMapping
  public ResponseEntity<String> addPatient(@Valid @RequestBody Patient patient,BindingResult bindingResult){ 
	  if (bindingResult.hasErrors()) {
			throw new ValidationFailedException("validation failed");
	  }
	  service.addPatientReport(patient); return new
  ResponseEntity<>("created",HttpStatus.OK); }
  
  @GetMapping public ResponseEntity<List<Patient>> getAllPatients(){ return new
  ResponseEntity<>(service.getallPatients(),HttpStatus.OK); }
  
  @GetMapping(value="/physician/{physicianId}") public ResponseEntity<List<Patient>>
  getPatientsByPhysicianId(@PathVariable Integer physicianId){ return new
  ResponseEntity<>(service.getPatientsByPhysicianId(physicianId),
  HttpStatus.OK); }
  
  @GetMapping(value="/{physicianId}") public
  ResponseEntity<Patient> getParticularPatient(@PathVariable int
  physicianId,@RequestParam(value="patientId") Integer patientId){ Patient pat =
  service.getParticularPatient(physicianId,patientId); return new
  ResponseEntity<>(pat,HttpStatus.OK); }
  
  @GetMapping(value="/insurance/{patientId}") public ResponseEntity<Integer>
  getInsuranceOfPatient(@PathVariable Integer patientId){ Integer insid =
  service.getInsuranceOfPatient(patientId); return new
  ResponseEntity<>(insid,HttpStatus.OK); }
  
  @PutMapping(value = "/address") public
  ResponseEntity<Patient> updateAddressOfPatient(@RequestParam("address") String address,@RequestParam("employeeId") Integer employeeId) { Patient patient =
  service.updateAddressOfPatient(employeeId,address); return new
  ResponseEntity<>(patient,HttpStatus.OK); }
  
  @PutMapping(value = "/phone") public
  ResponseEntity<Patient> updatePhoneOfPatient(@RequestParam("employeeId") Integer employeeId,@RequestParam("phone") String phone) { Patient patient =
  service.updatePhoneOfPatient(employeeId,phone); return new
  ResponseEntity<>(patient,HttpStatus.OK);
  
  } }
 