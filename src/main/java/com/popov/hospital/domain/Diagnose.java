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
     
    @ManyToMany(mappedBy = "diagnoses")
    private Set<Patient> patients;

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

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}
}
