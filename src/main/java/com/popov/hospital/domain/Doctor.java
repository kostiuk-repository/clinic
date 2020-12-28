package com.popov.hospital.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Doctor extends User{

    @Column(name = "post")
    private String post;
    @Column(name = "address")
    private String address;
    @Column(name = "fio")
    private String fio;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "telephone_number", unique = true)
    private String telephoneNumber;
    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients = new HashSet<>();

    public Doctor() {
    }

    public Doctor(String username, String passwordHash, String role, String post, String address, String fio, String email, String telephoneNumber) {
        super(username, passwordHash, role);
        this.post = post;
        this.address = address;
        this.fio = fio;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
