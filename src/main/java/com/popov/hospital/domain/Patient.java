package com.popov.hospital.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Patient {
	@Id
	@Column(name = "patient_id", insertable = false, updatable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "ward")
	private String ward;
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
	@Column(name = "dateOfRegistration")
    private Date dateOfRegistration;
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
	@Column(name = "dateOfDischarge")
    private Date dateOfDischarge;
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "patient_diagnose", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "diagnoseId") })
	private Set<Diagnose> diagnoses = new HashSet<Diagnose>(0);
	@ManyToOne
	@JoinColumn(name="id", nullable = false)
	private Doctor doctor;

    public Patient() {
    }

	public Patient(String firstName, String lastName, String ward, Doctor doctor, Date dateOfRegistration, Date dateOfDischarge) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ward = ward;
		this.doctor = doctor;
		this.dateOfRegistration = dateOfRegistration;
		this.dateOfDischarge = dateOfDischarge;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

    public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public Date getDateOfDischarge() {
		return dateOfDischarge;
	}

	public void setDateOfDischarge(Date dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
	}

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
