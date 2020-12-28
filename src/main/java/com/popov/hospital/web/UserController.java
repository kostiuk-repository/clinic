package com.popov.hospital.web;

import com.popov.hospital.domain.Doctor;
import com.popov.hospital.domain.DoctorRepository;
import com.popov.hospital.domain.SignupForm;
import com.popov.hospital.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private DoctorRepository doctorRepository;
	
    @RequestMapping(value = "signup")
    public String addPatient(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
    
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	System.out.println(bindingResult.toString());
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	Doctor newDoctor = new Doctor();
				newDoctor.setPasswordHash(hashPwd);
				newDoctor.setUsername(signupForm.getUsername());
				newDoctor.setRole(signupForm.getRole());
				newDoctor.setAddress(signupForm.getAddress());
				newDoctor.setEmail(signupForm.getEmail());
				newDoctor.setFio(signupForm.getFio());
				newDoctor.setPost(signupForm.getPost());
				newDoctor.setTelephoneNumber(signupForm.getTelephoneNumber());

		    	if (userRepository.findByUsername(signupForm.getUsername()) == null &&
						doctorRepository.findByUsername(signupForm.getUsername()) == null ) {
		    		doctorRepository.save(newDoctor);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "error.userexists", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
    
}
