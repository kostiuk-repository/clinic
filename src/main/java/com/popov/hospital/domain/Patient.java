package com.popov.hospital.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Patient {
	private long id;	 
	private String firstName;	
	private String lastName;
	private String ward;
    private String doctor;
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateOfRegistration;
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateOfDischarge;

	private Set<Diagnose> diagnoses = new HashSet<Diagnose>(0);
    
    public Patient() {
    }

	public Patient(String firstName, String lastName, String ward, String doctor, Date dateOfRegistration, Date dateOfDischarge) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ward = ward;
		this.doctor = doctor;
		this.dateOfRegistration = dateOfRegistration;
		this.dateOfDischarge = dateOfDischarge;
	}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    @Column(name = "firstname")   	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    @Column(name = "lastname")	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    @Column(name = "ward")
	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

    @Column(name = "doctor")
    public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	@Column(name = "getDateOfRegistration")
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	@Column(name = "getDateOfDischarge")
	public Date getDateOfDischarge() {
		return dateOfDischarge;
	}

	public void setDateOfDischarge(Date dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
	}

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "patient_diagnose", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "diagnoseId") })
	public Set<Diagnose> getDiagnoses() {
		return this.diagnoses;
	}

	public void setDiagnoses(Set<Diagnose> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public boolean hasDiagnose(Diagnose diagnose) {
		for (Diagnose patientDiagnose : getDiagnoses()) {
			if (patientDiagnose.getDiagnoseId() == diagnose.getDiagnoseId()) {
				return true;
			}
		}
		return false;
	}
}
