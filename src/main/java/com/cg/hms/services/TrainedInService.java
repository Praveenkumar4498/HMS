package com.cg.hms.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.hms.entities.Physician;
import com.cg.hms.entities.Procedures;
import com.cg.hms.entities.TrainedIn;

@Service
public interface TrainedInService {
	
	public void saveCertification(TrainedIn certification); 
	public List<Procedures> getTreatmentsByPhysicianId(int physicianId);
	public List<Physician> getPhysiciansByTreatmentId(int treatmentId);
	boolean updateCertificationExpiryDate(int physicianId, int procedureId, String expiryDate);
	

}
