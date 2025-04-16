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
  import com.cg.hms.entities.Procedures;
import com.cg.hms.exceptions.ValidationFailedException;
import com.cg.hms.services.ProcedureServiceImpl;
  
  
  @RestController
  @RequestMapping(value="/api/procedure")
  public class ProcedureController{
	  private ProcedureServiceImpl service;
  
  @Autowired public void setService(ProcedureServiceImpl service) {
  this.service = service;
  }
  
  @PostMapping 
  
public ResponseEntity<Procedures>saveProcedure(@Valid @RequestBody Procedures procedure, BindingResult bindingResult) {
	  
	  if (bindingResult.hasErrors()) {
			throw new ValidationFailedException("validation failed");
			}	  
      return new ResponseEntity<>(service.saveProcedure(procedure),HttpStatus.OK);
  }
  
  @GetMapping(value="/") 
  public ResponseEntity<List<Procedures>> getAllProcedures()
  {
  
  List<Procedures> procedures = service.getAllProcedures();
  return new ResponseEntity<>(procedures,HttpStatus.OK); 
  }
  
  @GetMapping(value="/cost") public ResponseEntity <Procedures>
  getCostOfProcedureById(@RequestParam(value="procedureId") int procedureId) {
	  Procedures cost = service.getCostOfProcedureById(procedureId); 
	  return new ResponseEntity<>(cost,HttpStatus.OK); 
	  }
  
  @GetMapping(value="/cost/{name}")
  public ResponseEntity<Procedures> getCostOfProcedureByName(@PathVariable String name) { 
  Procedures procedure = service.getCostOfProcedureByName(name);
  return new ResponseEntity<>(procedure,HttpStatus.OK); 
  }
  
  @PutMapping(value = "/cost") 
  public ResponseEntity <Procedures> updateCostOfProcedure(@RequestParam("cost") float cost,@RequestParam("employeeId") Integer employeeId) { 
  Procedures procedures = service.updateCostOfProcedure(employeeId,cost);
  return new ResponseEntity<>(procedures,HttpStatus.OK);  
  }
  
  @PutMapping(value = "/name") public
  ResponseEntity<Procedures> updateNameOfProcedure(@RequestParam("name") String name,@RequestParam("employeeId") Integer employeeId) { 
	  Procedures procedures = service.updateNameOfProcedure(employeeId,name); 
	  return new ResponseEntity<>(procedures,HttpStatus.OK); 
   } 
  }
  
 