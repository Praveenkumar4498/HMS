package com.cg.hms.entities;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

	@Id
	@NotNull(message="AppointmnetID cannot be empty")
	private Integer appointmentId;
	@ManyToOne
	@JoinColumn(name="Patient")
	@NotNull(message="Patient cannot be Empty")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name="PrepNurse")
	@NotNull(message="PrepNurse cannot be Empty")
	private Nurse nurse;
	@ManyToOne
	@JoinColumn(name="Physician")
	@NotNull(message="Physician cannot be Empty")
	private Physician physician;
	@Column(name="Starto")
	@NotNull(message="StartTime cannot be Empty")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
	private Timestamp startTime;
	@Column(name="Endo")
	@NotNull(message="EndTime cannot be Empty")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
	private Timestamp endTime;
	@Column(name="ExaminationRoom",columnDefinition = "text")
	private String examinationRoom;
	}
