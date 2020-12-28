package com.popov.hospital;

import com.popov.hospital.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	

	@Bean
	public CommandLineRunner demo(PatientRepository patientRepository, DiagnoseRepository diagnoseRepository, UserRepository userRepository, DoctorRepository doctorRepository) {
		return (args) -> {

			User user1 = new User("nurse", "$2a$10$23/NRBBF26nS0wB1C8rB1OMAyFqG8F5HvQ80YBbs7mUfX1ackKVH.", "NURSE");
			Doctor doctor = new Doctor("doctor", "$2a$10$Q1E294xfgpygT8xi/Mrk4u9XXP/UwuvCaMufKCU2vCjRZWevLgww6",
						"DOCTOR", "Chief Medical Officer", "Moskow", "Hippocrat Oleg Pavlovich", "testdoctor@gmail.com", "88005553535");
			User user3 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			userRepository.save(user1);
			doctorRepository.save(doctor);
			userRepository.save(user3);

			Patient patientOne = new Patient("John", "Johnson", "1", doctor,
					new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), new Date());
			patientRepository.save(patientOne);
			Patient patientTwo = new Patient("Steve", "Stevens", "2", doctor,
					new Date(), new Date());
			patientRepository.save(patientTwo);
			Patient patientThree = new Patient("Mary", "Robinson", "2", doctor,
					new Date(), new Date());
			patientRepository.save(patientThree);
			Patient patientFour = new Patient("Kate", "Keystone", "4", doctor,
					new Date(), new Date());
			patientRepository.save(patientFour);
			Patient patientFive = new Patient("Diana", "Doll", "5", doctor,
					new Date(), new Date());
			patientRepository.save(patientFive);

			Stream.of("Cold", "Grip", "Coronavirus", "Inflammation stealth").forEach(name -> {
				diagnoseRepository.save(new Diagnose(name));
			});

			doctor.setPatients(Set.of(patientOne, patientTwo, patientThree, patientFour, patientFive));
			doctorRepository.save(doctor);
		};
	}
}
