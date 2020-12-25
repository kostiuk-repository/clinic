package com.popov.hospital.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findByLastName(String lastname);
    
}
