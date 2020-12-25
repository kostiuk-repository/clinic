package com.popov.hospital;

import com.popov.hospital.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	

	@Bean
	public CommandLineRunner demo(PatientRepository repository, DiagnoseRepository diagnoseRepository, UserRepository urepository) {
		return (args) -> {
			repository.save(new Patient("John", "Johnson", "IT", "john@john.com",
					new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), new Date()));
			repository.save(new Patient("Steve", "Stevens", "IT", "steve.stevent@st.com",
					new Date(), new Date()));
			repository.save(new Patient("Mary", "Robinson", "IT", "mary@robinson.com",
					new Date(), new Date()));
			repository.save(new Patient("Kate", "Keystone", "Nursery","kate@kate.com",
					new Date(), new Date()));
			repository.save(new Patient("Diana", "Doll", "Business","diana@doll.com",
					new Date(), new Date()));
			
			Stream.of("Cold", "Grip", "Coronavirus", "Inflammation stealth").forEach(name -> {
				diagnoseRepository.save(new Diagnose(name));
			});

			User user1 = new User("nurse", "$2a$10$23/NRBBF26nS0wB1C8rB1OMAyFqG8F5HvQ80YBbs7mUfX1ackKVH.", "NURSE");
			User user2 = new User("doctor", "$2a$10$Q1E294xfgpygT8xi/Mrk4u9XXP/UwuvCaMufKCU2vCjRZWevLgww6", "DOCTOR");
			User user3 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			urepository.save(user1);
			urepository.save(user2); 
			urepository.save(user3);
		};
	}
}
