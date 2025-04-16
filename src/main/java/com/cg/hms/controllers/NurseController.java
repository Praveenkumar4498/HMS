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

import com.cg.hms.entities.Nurse;
import com.cg.hms.exceptions.ValidationFailedException;
import com.cg.hms.services.NurseServiceImpl;

@RestController
@RequestMapping(value="/api/nurse")
public class NurseController {
private NurseServiceImpl service;
@Autowired
public void setService(NurseServiceImpl service) {
	this.service = service;
}
@PostMapping(value="/")
public ResponseEntity<Nurse> addNurse(@Valid @RequestBody Nurse nurse,BindingResult bindingResult) {
	
	 if (bindingResult.hasErrors()) {
			throw new ValidationFailedException("validation failed");
			}
return new ResponseEntity<>(service.saveNurse(nurse),HttpStatus.CREATED);
     }
@GetMapping(value="/")
	public ResponseEntity<List<Nurse>> getallNurses() {
	    return new ResponseEntity<>(service.getallNurses(),HttpStatus.OK);
	}
@GetMapping(value="/{employeeId}")
	public ResponseEntity<Nurse> getDetailOfNurseByemployeeId(@PathVariable Integer employeeId){
		Nurse nurse = service.getDetailOfNurseByemployeeId(employeeId);
		return new ResponseEntity<>(nurse,HttpStatus.OK);
		}
	@GetMapping(value="/position/{employeeId}")
		public ResponseEntity<String> getPositionOfNurseByemployeeId(@PathVariable Integer employeeId) {
			
            String position = service.getPositionOfNurseByemployeeId(employeeId);
			return new ResponseEntity<>(position,HttpStatus.OK);
		}
	@GetMapping(value="/registered/{employeeId}")
			public ResponseEntity<Boolean> nurseIsRegisteredOrNot(@PathVariable Integer employeeId) {
				
                Boolean register = service.nurseIsRegisteredOrNot(employeeId);
				return new ResponseEntity<>(register,HttpStatus.OK);
			}
	@PutMapping(value = "/registered")
	public ResponseEntity<Nurse> updateValueOfregistred(@RequestParam("register") Boolean register,@RequestParam("employeeId") Integer employeeId) {
					Nurse nurse = service.updateValueOfregistred(employeeId,register);
		            return new ResponseEntity<>(nurse,HttpStatus.OK);
				}
	@PutMapping(value = "/ssn")
		public ResponseEntity<Nurse> updateValueOfregistred(@RequestParam("ssn") Integer ssn,@RequestParam("employeeId") Integer employeeId) {
		        Nurse nurse = service.updateValueOfSSN(employeeId,ssn);
		        return new ResponseEntity<>(nurse,HttpStatus.OK);
			}
}
