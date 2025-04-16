package com.cg.hms.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hms.entities.Appointment;
import com.cg.hms.entities.Nurse;
import com.cg.hms.entities.Patient;
import com.cg.hms.entities.Physician;
import com.cg.hms.entities.Room;
import com.cg.hms.entities.Stay;
import com.cg.hms.exceptions.DuplicateEntryException;
import com.cg.hms.exceptions.NoEntryException;
import com.cg.hms.exceptions.NoRecordsException;
import com.cg.hms.exceptions.NoSuchElementException;
import com.cg.hms.repositories.AppointmentRepository;
import com.cg.hms.repositories.NurseRepository;
import com.cg.hms.repositories.PatientRepository;
import com.cg.hms.repositories.PhysicianRepository;
import com.cg.hms.repositories.StayRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	private static final String NOSUCHAPP = "Appointment record for the given id is not available";
	
	
	private AppointmentRepository appointmentRepository;
	
	private PatientRepository patientRepository;
	
	@Autowired
	public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	@Autowired
	public void setService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public Appointment saveAppointment(Appointment appointment) {
		if(appointmentRepository.findById(appointment.getAppointmentId()).isEmpty())
			return appointmentRepository.save(appointment);
		throw new DuplicateEntryException("Appointment with the given id is already existed");
		
	}

	@Override
	public List<Appointment> getallAppointments() {
		
		List<Appointment> list = appointmentRepository.findAll();
		if(list.isEmpty())
		 throw new NoRecordsException("No Appointments found");
		return list;
		
    }
	
	@Override
	public List<Appointment> getallAppointmentsByStartDate(Timestamp startdate) {
		
		
		List<Appointment> appointments = appointmentRepository.findByStartTime(startdate);
		if(appointments.isEmpty())
		 throw new NoSuchElementException("Appointment record for the given startdate is not available");
		return appointments;
    }
    

	@Override
	public Patient getPatientInfoByAppointmentId(Integer appointementId) {
		 return appointmentRepository.findById(appointementId).orElseThrow(()-> new NoSuchElementException(NOSUCHAPP)).getPatient();
	    }

	@Override
	public Physician getPhysicianDetailByAppointmentId(int appointmentId) {
		return appointmentRepository.findById(appointmentId).orElseThrow(()-> new NoSuchElementException(NOSUCHAPP)).getPhysician();
    }
	
	@Override
	public Nurse getNurseDeatailByAppointmentId(int appointmentId) {
		 return appointmentRepository.findById(appointmentId).orElseThrow(()-> new NoSuchElementException(NOSUCHAPP)).getNurse();
	    }

	@Override
	public String getExaminationRoomByAppointmentId(int appointmentId) {
		 return appointmentRepository.findById(appointmentId).orElseThrow(()-> new NoSuchElementException(NOSUCHAPP)).getExaminationRoom();
	    }
	

	@Override
	public List<Physician> getPhysiciansByPatientId(int patientId) {
		return findByPatientId(patientId).stream().map(x->x.getPhysician()).collect(Collectors.toList());
	}

	@Override
	public Physician getPhysicianDetailByPatientIdAndDate(int patientId, Timestamp date) {
		return findByPatientId(patientId).stream().filter(p -> p.getStartTime().equals(date))
				.map(p -> p.getPhysician()).findFirst().orElseThrow(()-> new NoSuchElementException("appointment not scheduled for given patient on provided date")); 
    }

	@Override
	public List<Nurse> getNursesByPatientId(int patientId) {
		
		return findByPatientId(patientId).stream().map(x->x.getNurse()).collect(Collectors.toList());
    }
	

	@Override
	public Physician getPhysicianDetailByNurseIdAndDate(int nurseId, Timestamp date) {
		return findByNurseId(nurseId).stream().filter(x->x.getStartTime().equals(date)).map(x->x.getPhysician()).findFirst().orElseThrow(()-> new NoSuchElementException("Mentioned Nurse do not have duty on given date"));
	    }
	

	@Override
	public List<Timestamp> getAppointmentDatesByPatientId(int patientId) {
        return findByPatientId(patientId).stream().map(x->x.getStartTime()).collect(Collectors.toList());
    }
		

	@Override
	public List<Patient> getPatientsCheckedByPhysician(int physicianId) {
		return findByPhysicianId(physicianId).stream().map(x->x.getPatient()).collect(Collectors.toList());
    }

	@Override
	public List<Patient> getPatientsCheckedByPhysicianOnDate(int physicianId, Timestamp date) {
		List<Patient> patients = findByPhysicianId(physicianId).stream().filter(i->i.getStartTime().equals(date)).map(x->x.getPatient()).collect(Collectors.toList());
		if(patients.isEmpty())
			throw new NoSuchElementException("No Appointments on that date for given Physician");
		return patients;
	}
	

	@Override
	public Patient getPatientCheckedByPhysician(int patientId, Integer physicianId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(()->new NoSuchElementException("patient with the id is not available"));
		return findByPhysicianId(physicianId).stream().filter(x->x.getPatient().equals(patient)).findFirst().orElseThrow(()->new NoSuchElementException("Given patient do not have appointment with the mentioned physician")).getPatient();
    }
	

	@Override
	public List<Patient> getPatientsCheckedByNurse(int nurseId) {
		return findByNurseId(nurseId).stream().map(x->x.getPatient()).collect(Collectors.toList());
    }
	@Override
	public Patient getPatientCheckedByNurse(int nurseid,int patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new NoSuchElementException("Patient with the given id is not available"));
		return findByNurseId(nurseid).stream().filter(x->x.getPatient().equals(patient)).findFirst().orElseThrow(()-> new NoSuchElementException("Appointment not scheduled nurse with the given patient")).getPatient();
		
    }
	

	@Override
	public List<Patient> getPatientsCheckedByNurseOnDate(int nurseId, Timestamp date) {
		List<Appointment> appointment = appointmentRepository.findByNurse(nurseId);
		List<Patient> patients = appointment.stream().filter(a->a.getStartTime().equals(date)).map(x->x.getPatient()).collect(Collectors.toList());
		if(patients.isEmpty())
			throw new NoSuchElementException("No appointment schedules for given nurse on that date");
	    return patients;
    }
	
	@Override
	public Patient getPatientCheckedByPhysician(Integer physicianId) {
		return findByPhysicianId(physicianId).stream().findFirst().orElseThrow(()-> new NoSuchElementException("No appointment scheduled for given physician")).getPatient();
	}
	///
	public List<Appointment> findByPhysicianId(Integer physicianId){
		List<Appointment> appointments = appointmentRepository.findByPhysician(physicianId);
		if(appointments.isEmpty())
			throw new NoEntryException("Appointments not scheduled for the given Physician");
		return appointments;
	}
	public List<Appointment> findByPatientId(Integer patientId){
		List<Appointment> appointments = appointmentRepository.findByPatient(patientId);
		if(appointments.isEmpty())
			throw new NoEntryException("Appointments not scheduled for the given patient");
		return appointments;
	}
	public List<Appointment> findByNurseId(Integer nurseId){
		List<Appointment> appointments = appointmentRepository.findByNurse(nurseId);
		if(appointments.isEmpty())
			throw new NoEntryException("Appointments not scheduled for the given patient");
		return appointments;
	}
	
	
	
	

}
