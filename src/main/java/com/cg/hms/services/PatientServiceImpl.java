
  package com.cg.hms.services;
  
  import java.util.List; import java.util.Optional;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;

import com.cg.hms.entities.Patient;
import com.cg.hms.entities.Physician;
import com.cg.hms.exceptions.DuplicateEntryException;
import com.cg.hms.exceptions.NoEntryException;
import com.cg.hms.exceptions.NoRecordsException;
import com.cg.hms.exceptions.NoSuchElementException;
import com.cg.hms.repositories.PatientRepository;
import com.cg.hms.repositories.PhysicianRepository;
  @Service
  public class PatientServiceImpl implements PatientService{
  private final String NOSUCH = "No patient record available for given id";
  
  private PatientRepository patientRepository;
  
  private PhysicianRepository physicianRepository;
  
  @Autowired 
  public void setPatientRepository(PatientRepository patientRepository) {
  this.patientRepository = patientRepository;
  }
  
  @Autowired 
  public void setphysicianRepository(PhysicianRepository physicianRepository){
  this.physicianRepository = physicianRepository;
  }
  
  @Override 
  public Patient addPatientReport(Patient patient) {
	  if(patientRepository.findById(patient.getSsn()).isEmpty())
		  return patientRepository.save(patient);
	  throw new DuplicateEntryException("Patient record with given id is already existed");
	  
	  }
  
  @Override
  public List<Patient> getallPatients() {
 
  List<Patient> patients = patientRepository.findAll();
  if(patients.isEmpty())
    throw new NoRecordsException("No records in the given table");
  return patients;
  }
  
  @Override
  public List<Patient> getPatientsByPhysicianId(Integer physicianId) {
  Physician physician = physicianRepository.findById(physicianId).orElseThrow(()-> new NoSuchElementException(NOSUCH));
  List<Patient> patients = patientRepository.findByPcp(physician);
  if(patients.isEmpty())
   throw new NoEntryException("");
  return patients;
  }
  
  @Override 
  public Patient getParticularPatient(Integer physicianId,Integer patientId) {
  Physician physician = physicianRepository.findById(physicianId).orElseThrow(()-> new NoSuchElementException("No physician availabe with the given id"));
  List<Patient> patients = patientRepository.findByPcp(physician);
  Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new NoSuchElementException(NOSUCH));
  return patients.stream().filter(x -> x.equals(patient)).findFirst().orElseThrow(()-> new NoSuchElementException(NOSUCH));
  }
  
  @Override
  public Integer getInsuranceOfPatient(Integer patientId) {
  return patientRepository.findById(patientId).orElseThrow(()-> new NoSuchElementException(NOSUCH)).getInsuranceId();
  }
  
  
  @Override
  public Patient updateAddressOfPatient(Integer ssn,String address)
  {
  Patient patient1 = patientRepository.findById(ssn).orElseThrow(()-> new NoSuchElementException(NOSUCH));
  patient1.setAddress(address);
  return patientRepository.save(patient1);
  }
  @Override public Patient updatePhoneOfPatient(Integer ssn, String phone) {
  Patient patient1 = patientRepository.findById(ssn).orElseThrow(()-> new NoSuchElementException(NOSUCH));
  patient1.setPhone(phone);
  return patientRepository.save(patient1);
  
  }
  }
  
  
  
 