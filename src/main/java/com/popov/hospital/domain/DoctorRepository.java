package com.popov.hospital.domain;

import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Doctor findByUsername(String username);
}
