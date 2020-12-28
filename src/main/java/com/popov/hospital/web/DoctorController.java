package com.popov.hospital.web;

import com.popov.hospital.domain.Doctor;
import com.popov.hospital.domain.DoctorRepository;
import com.popov.hospital.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @RequestMapping("/doctors")
    public String index(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "doctors";
    }
    @RequestMapping(value = "/edit/doctor/{id}")
    public String editPatient(@PathVariable("id") Long doctorId, Model model){
        model.addAttribute("doctor", doctorRepository.findById(doctorId));
        return "editDoctor";
    }

    @RequestMapping(value = "/delete/doctor/{id}", method = RequestMethod.GET)
    public String deletePatient(@PathVariable("id") Long doctorId, Model model) {
        doctorRepository.deleteById(doctorId);
        return "redirect:/doctors";
    }
}
