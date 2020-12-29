package com.popov.hospital.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnoseRepository extends CrudRepository<Diagnose, Long>  {
    
	List<Diagnose> findByName(String name);

	List<Diagnose> findByPatientId(Long id);
}

