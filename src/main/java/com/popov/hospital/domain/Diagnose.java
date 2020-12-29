package com.popov.hospital.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Diagnose {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	private long diagnoseId;
	
    @Column(name="diagnosename")
	private String name;

	@Column(name="therapy")
	private String therapy;

	@Column(name="symptoms")
	private String symptoms;

	@ManyToOne
	@JoinColumn(name="id", nullable = false)
    private Patient patient;

    public Diagnose() {
	}

	public Diagnose(String name) {
		this.name = name;
	}

	public long getDiagnoseId() {
		return diagnoseId;
	}

	public void setDiagnoseId(long diagnoseId) {
		this.diagnoseId = diagnoseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getTherapy() {
		return therapy;
	}

	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
}
