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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @RequestMapping("/doctors")
    public String index(Model model,
                        @RequestParam(value="fio", required=false) String fio,
                        @RequestParam(value="post", required=false) String post,
                        @RequestParam(value="address", required=false) String address,
                        @RequestParam(value="email", required=false) String email,
                        @RequestParam(value="telephoneNumber", required=false) String telephoneNumber) {
        List<Doctor> doctorRepositoryAll = (List<Doctor>) doctorRepository.findAll();
        if(fio != null && !fio.isEmpty()){
            doctorRepositoryAll.removeAll(doctorRepositoryAll.stream().filter(doctor -> !doctor.getFio().equalsIgnoreCase(fio)).collect(Collectors.toList()));
        }
        if(post != null && !post.isEmpty()){
            doctorRepositoryAll.removeAll(doctorRepositoryAll.stream().filter(doctor -> !doctor.getPost().equalsIgnoreCase(post)).collect(Collectors.toList()));
        }
        if(address != null && !address.isEmpty()){
            doctorRepositoryAll.removeAll(doctorRepositoryAll.stream().filter(doctor -> !doctor.getAddress().equalsIgnoreCase(address)).collect(Collectors.toList()));
        }
        if(email != null && !email.isEmpty()){
            doctorRepositoryAll.removeAll(doctorRepositoryAll.stream().filter(doctor -> !doctor.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList()));
        }
        if(telephoneNumber != null && !telephoneNumber.isEmpty()){
            doctorRepositoryAll.removeAll(doctorRepositoryAll.stream().filter(doctor -> !doctor.getTelephoneNumber().equalsIgnoreCase(telephoneNumber)).collect(Collectors.toList()));
        }
        model.addAttribute("doctors", doctorRepositoryAll);
        return "doctors";
    }
    @RequestMapping(value = "/edit/doctor/{id}")
    public String editPatient(@PathVariable("id") Long doctorId, Model model){
        model.addAttribute("doctor", doctorRepository.findById(doctorId));
        return "editDoctor";
    }

    @RequestMapping(value = "/edit/doctor/saveDoctor", method = RequestMethod.POST)
    public String save(Doctor doctor){
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getId());
        Doctor doctorEdit = doctorOptional.get();
        doctorEdit.setFio(doctor.getFio());
        doctorEdit.setPost(doctor.getPost());
        doctorEdit.setTelephoneNumber(doctor.getTelephoneNumber());
        doctorEdit.setAddress(doctor.getAddress());
        doctorEdit.setEmail(doctor.getEmail());
        doctorRepository.save(doctorEdit);
        return "redirect:/doctors";
    }

    @RequestMapping(value = "/delete/doctor/{id}", method = RequestMethod.GET)
    public String deletePatient(@PathVariable("id") Long doctorId, Model model) {
        doctorRepository.deleteById(doctorId);
        return "redirect:/doctors";
    }
}
