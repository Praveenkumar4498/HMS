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
import com.cg.hms.entities.Procedures;
import com.cg.hms.entities.TrainedIn;
import com.cg.hms.exceptions.ValidationFailedException;
import com.cg.hms.services.TrainedInServiceImpl;

 

@RestController
@RequestMapping(value="/api/trained_in")
public class TrainedInController {
private TrainedInServiceImpl service;
@Autowired
public void setService(TrainedInServiceImpl service) {
    this.service = service;
}
@PostMapping
public ResponseEntity<String> save(@Valid @RequestBody TrainedIn trainedIn, BindingResult bindingResult){
	 if (bindingResult.hasErrors()) {
			throw new ValidationFailedException("validation failed");
			}
    service.saveCertification(trainedIn);
    return new ResponseEntity<>("success",HttpStatus.OK);
}
@GetMapping(value="/treatment/{physicianId}")
public ResponseEntity<List<Procedures>> getTreatmentsByPhysicianId(@PathVariable int physicianId){
    List<Procedures> procedures = service.getTreatmentsByPhysicianId(physicianId);
    return new ResponseEntity<>(procedures,HttpStatus.OK);
}
@GetMapping(value ="/physician/{procedureId}")
public ResponseEntity<List<Physician>> getPhysiciansByTreatmentId(@PathVariable int procedureId){
    List<Physician> physicians = service.getPhysiciansByTreatmentId(procedureId);
    return new ResponseEntity<>(physicians,HttpStatus.OK);
}
@PutMapping(value="/certificationexpiry")
public ResponseEntity<Boolean> updateCertificationExpiryDate(@RequestParam("expiryDate") String expiryDate,@RequestParam("physicianId") int physicianId,@RequestParam("procedureId") int procedureId){
    Boolean value = service.updateCertificationExpiryDate(physicianId, procedureId,expiryDate);
    return new ResponseEntity<>(value,HttpStatus.OK);
}
}