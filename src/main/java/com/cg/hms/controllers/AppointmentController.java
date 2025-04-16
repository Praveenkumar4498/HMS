
  package com.cg.hms.controllers;
  
  
  
  import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.PathVariable; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import
  org.springframework.web.bind.annotation.RequestParam; import
  org.springframework.web.bind.annotation.RestController;

import com.cg.hms.entities.Appointment;
import com.cg.hms.entities.Nurse;
import com.cg.hms.entities.Patient;
import com.cg.hms.entities.Physician;
import com.cg.hms.entities.Room;
import com.cg.hms.exceptions.ValidationFailedException;
import com.cg.hms.services.AppointmentServiceImpl;
  
  
  
  
  @RestController
  @RequestMapping(value="/api/appointment")
  public class AppointmentController{
  private AppointmentServiceImpl service;
  
  @Autowired public void setService(AppointmentServiceImpl service) {
  this.service = service;
  } //1
  
  @PostMapping(value="/")
public ResponseEntity <String> saveAppointment(@Valid @RequestBody Appointment appointment,BindingResult bindingResult) {
	  
	  if (bindingResult.hasErrors()) {
			throw new ValidationFailedException("validation failed");
			}
	  
  service.saveAppointment(appointment);
  return new ResponseEntity<>("Successfully Created",HttpStatus.OK);
  } //2
  
  @GetMapping public ResponseEntity <List<Appointment>> getallAppointments() {
  List<Appointment> appointments = service.getallAppointments();
  return new ResponseEntity<>(appointments,HttpStatus.OK);
  } //3
  
  @GetMapping(value="/{startDate}")
  public ResponseEntity <List<Appointment>> getallAppointmentsByStartDate(@PathVariable Timestamp startDate) {
  List<Appointment> appointments = service.getallAppointmentsByStartDate(startDate);
  return new ResponseEntity<>(appointments,HttpStatus.OK);
  } //4
  @GetMapping(value="/physician/{appointmentid}") public
  ResponseEntity<Physician> getPhysicianDetailByAppointmentId(@PathVariable int appointmentId) {
  Physician physician = service.getPhysicianDetailByAppointmentId(appointmentId);
  return new ResponseEntity<>(physician,HttpStatus.OK);
  } //6
  
  @GetMapping(value="/nurse/{appointmentId}")
  public ResponseEntity<Nurse> getNurseDeatailByAppointmentId(@PathVariable int appointmentId) {
  Nurse nurse = service.getNurseDeatailByAppointmentId(appointmentId);
  return new ResponseEntity<>(nurse,HttpStatus.OK);
  } //7
  
  @GetMapping(value="/examinationroom/{appointmentId}")
  public ResponseEntity <String> getExaminationRoomByAppointmentId(@PathVariable int appointmentId) {
  String room = service.getExaminationRoomByAppointmentId(appointmentId);
  return new ResponseEntity<>(room,HttpStatus.OK);
  } //8
  
  @GetMapping(value="/physicians") 
  public ResponseEntity<List<Physician>> getPhysiciansByPatientId(@RequestParam("patientid") int patientid) {
  List<Physician> appointments = service.getPhysiciansByPatientId(patientid);
  return new ResponseEntity<>(appointments,HttpStatus.OK); 
  }
  @GetMapping(value="/patient/{appointmentId}")
  public ResponseEntity<Patient> getPatientByAppointmentId(@PathVariable Integer appointmentId){
	  Patient patient = service.getPatientInfoByAppointmentId(appointmentId);
	  return new ResponseEntity<>(patient,HttpStatus.OK);
  }
  @GetMapping(value="/patient/{patientId}/{physicianId}")
  public ResponseEntity<Patient> getPatientByPatientIdAndPhysicianId(@PathVariable Integer patientId,@PathVariable Integer physicianId){
	  Patient patient = service.getPatientCheckedByPhysician(patientId,physicianId);
	  return new ResponseEntity<>(patient,HttpStatus.OK); 
  }
  @GetMapping(value="/patient/physician/{physicianId}")
  public ResponseEntity<List<Patient>> getPatientByPhysicianId(@PathVariable Integer physicianId){
	  List<Patient> patients = service.getPatientsCheckedByPhysician(physicianId);
	  return new ResponseEntity<>(patients,HttpStatus.OK);
  }
  @GetMapping(value="/patient/nurse/{nurseId}")
  public ResponseEntity<List<Patient>> getPatientByNurseId(@PathVariable Integer nurseId){
	  List<Patient> patients = service.getPatientsCheckedByNurse(nurseId);
	  return new ResponseEntity<>(patients,HttpStatus.OK);
  }
  @GetMapping(value="/patient/nurse/{patientId}/{nurseId")
  public ResponseEntity<Patient> getPatientByPatientIdAndnurseId(@PathVariable Integer patientId,@PathVariable Integer nurseId){
	  Patient patient = service.getPatientCheckedByNurse(nurseId,patientId);
	  return new ResponseEntity<>(patient,HttpStatus.OK); 
  }
  @GetMapping(value="/patient/date/nurse/{nurseId}/{date}")
  public ResponseEntity<List<Patient>> getPatientBynurseIdAndDate(@PathVariable Integer nurseId,@PathVariable Timestamp date){
	  List<Patient> patients = service.getPatientsCheckedByNurseOnDate(nurseId, date);
	  return new ResponseEntity<>(patients,HttpStatus.OK);
  }
  @GetMapping(value="/patient/date/physician/{physicianId}/{date}")
  public ResponseEntity<List<Patient>> getPatientByPhysicianIdIdAndDate(@PathVariable Integer physicianId,@PathVariable Timestamp date){
	  List<Patient> patients = service.getPatientsCheckedByPhysicianOnDate(physicianId, date);
	  return new ResponseEntity<>(patients,HttpStatus.OK);  
  }
  
  @GetMapping(value = "/physician/date")
  public ResponseEntity<Physician> getPhysicianDetailByPatientIdAndDate(@RequestParam("patientId") int patientId,@RequestParam("date")Timestamp date) { 
  Physician physician = service.getPhysicianDetailByPatientIdAndDate(patientId,date);
  return new ResponseEntity<>(physician,HttpStatus.OK);
  } //10
  
  @GetMapping(value="/nurses")
  public ResponseEntity<List<Nurse>> getNursesByPatientId(@RequestParam("patientId") int patientId) {
  List<Nurse> nurses = service.getNursesByPatientId(patientId);
  return new ResponseEntity<>(nurses,HttpStatus.OK);
  } //11
  
  @GetMapping(value = "/nurse/date")
  public ResponseEntity<Physician> getPhysicianDetailByNurseIdAndDate(@RequestParam("physicianId") int physicianId,@RequestParam("date") Timestamp date) {
  Physician physician = service.getPhysicianDetailByNurseIdAndDate(physicianId,date); 
  return new ResponseEntity<>(physician,HttpStatus.OK);
  } //12
  
  @GetMapping(value="/date/{patientId}")
  public ResponseEntity<List<Timestamp>> getAppointmentDatesByPatientId(@PathVariable int patientId) {
  List<Timestamp> dates = service.getAppointmentDatesByPatientId(patientId);
  return new ResponseEntity<>(dates,HttpStatus.OK);
  } //13
  }
 