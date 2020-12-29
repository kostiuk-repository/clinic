package com.popov.hospital.web;

import com.popov.hospital.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PatientController {
	@Autowired
    private PatientRepository patientRepository;

	@Autowired
    private DiagnoseRepository diagnoseRepository;

	@Autowired
	private DoctorRepository doctorRepository;
	
	@RequestMapping("/login")
	public String login() {
    	return "login";
    }	
	
	@RequestMapping("/patients")
	public String index(Model model,
						@RequestParam(value="firstname", required=false) String firstname,
						@RequestParam(value="lastname", required=false) String lastname,
						@RequestParam(value="ward", required=false) String ward,
						@RequestParam(value="doctorName", required=false) String doctorName,
						@RequestParam(value="dateOfRegistration", required=false) String dateOfRegistration,
						@RequestParam(value="dateOfDischarge", required=false) String dateOfDischarge) {
			List<Patient> patients = (List<Patient>) patientRepository.findAll();

			if(firstname != null && !firstname.isEmpty()){
				patients.removeAll(patients.stream().filter(patient -> !patient.getFirstName().equalsIgnoreCase(firstname)).collect(Collectors.toList()));
			}
			if(lastname != null && !lastname.isEmpty()){
				patients.removeAll(patients.stream().filter(patient -> !patient.getLastName().equalsIgnoreCase(lastname)).collect(Collectors.toList()));
			}
			if(ward != null && !ward.isEmpty()){
				patients.removeAll(patients.stream().filter(patient -> !patient.getWard().equalsIgnoreCase(ward)).collect(Collectors.toList()));
			}
			if(doctorName != null && !doctorName.isEmpty()){
				patients.removeAll(patients.stream().filter(patient -> !patient.getDoctor().getFio().equalsIgnoreCase(doctorName)).collect(Collectors.toList()));
			}
			model.addAttribute("patients", patients);
			model.addAttribute("doctors", doctorRepository.findAll());
    	return "patients";
    }

    @RequestMapping(value = "add")
    public String addPatient(Model model){
    	model.addAttribute("patient", new Patient());
		model.addAttribute("doctors", doctorRepository.findAll());
        return "addPatient";
    }	

    @RequestMapping(value = "/edit/{id}")
    public String editPatient(@PathVariable("id") Long patientId, Model model){
    	model.addAttribute("patient", patientRepository.findById(patientId));
        return "editPatient";
    }	    
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Patient patient){
        patientRepository.save(patient);
    	return "redirect:/patients";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePatient(@PathVariable("id") Long patientId, Model model) {
    	patientRepository.deleteById(patientId);
        return "redirect:/patients";
    }    
    
    @RequestMapping(value = "addPatientDiagnose/{id}", method = RequestMethod.GET)
    public String addDiagnose(@PathVariable("id") Long patientId, Model model){

    		model.addAttribute("diagnoses", diagnoseRepository.findAll());
    		model.addAttribute("patient", patientRepository.findById(patientId).get());
    		return "addPatientDiagnose";
    }
    
    
    @RequestMapping(value="/patient/{id}/diagnoses", method=RequestMethod.GET)
	public String patientsAddDiagnose(@RequestParam(value="action", required=true) String action, @PathVariable Long id, @RequestParam Long diagnoseId, Model model) {
    	Optional<Diagnose> diagnose = diagnoseRepository.findById(diagnoseId);
		Optional<Patient> patient = patientRepository.findById(id);

		if (patient.isPresent() && action.equalsIgnoreCase("save")) {
			if (!patient.get().hasDiagnose(diagnose.get())) {
				patient.get().getDiagnoses().add(diagnose.get());
			}
			patientRepository.save(patient.get());
			model.addAttribute("patient", diagnoseRepository.findById(id));
			model.addAttribute("diagnoses", diagnoseRepository.findAll());
			return "redirect:/patients";
		}

		model.addAttribute("developers", patientRepository.findAll());
		return "redirect:/patients";
		
	}    
    
    @RequestMapping(value = "getPatient", method = RequestMethod.GET)
    public @ResponseBody List<Patient> getPatients() {
            return (List<Patient>) patientRepository.findAll();
    }
}
