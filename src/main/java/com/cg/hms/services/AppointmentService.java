package com.cg.hms.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.hms.entities.Appointment;
import com.cg.hms.entities.Nurse;
import com.cg.hms.entities.Patient;
import com.cg.hms.entities.Physician;
import com.cg.hms.entities.Room;

@Service
public interface AppointmentService {
	
	public Appointment saveAppointment(Appointment appointment);
	public List<Appointment> getallAppointments();
	public Patient getPatientInfoByAppointmentId(Integer appointementId);
	public Physician getPhysicianDetailByAppointmentId(int appointmentId);
	public Nurse getNurseDeatailByAppointmentId(int appointmentId);
	public String getExaminationRoomByAppointmentId(int appointmentId);  
	public List<Physician> getPhysiciansByPatientId(int patientId);
	public List<Nurse> getNursesByPatientId(int patientId);
	public List<Timestamp> getAppointmentDatesByPatientId(int patientId);
	public List<Patient> getPatientsCheckedByPhysician(int physicianId);
	public List<Patient> getPatientsCheckedByPhysicianOnDate(int physicianId, Timestamp date);
	public Patient getPatientCheckedByPhysician(Integer patientId);
	public List<Patient> getPatientsCheckedByNurse(int nurseId);
	public List<Patient> getPatientsCheckedByNurseOnDate(int nurseId, Timestamp date);
	List<Appointment> getallAppointmentsByStartDate(Timestamp startdate);
	Physician getPhysicianDetailByPatientIdAndDate(int patientId, Timestamp date);
	Physician getPhysicianDetailByNurseIdAndDate(int patientId, Timestamp date);
	Patient getPatientCheckedByPhysician(int patientId, Integer physicianId);
	Patient getPatientCheckedByNurse(int nurseid, int patientId);
	
	

}
