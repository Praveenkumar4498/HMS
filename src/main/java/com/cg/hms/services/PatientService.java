package com.cg.hms.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.stereotype.Service;

import com.cg.hms.entities.Patient;

@Service
public interface PatientService {
	
	public Patient addPatientReport(Patient report);
	public List<Patient> getallPatients();
	public List<Patient> getPatientsByPhysicianId(Integer pid);
	public Patient getParticularPatient(Integer physicianid,Integer ssn);
	public Integer getInsuranceOfPatient(Integer patientid);
	public Patient updateAddressOfPatient(Integer ssn,String address);
	public Patient updatePhoneOfPatient(Integer ssn,String phone);
	

}
