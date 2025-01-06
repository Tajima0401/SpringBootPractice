package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminSignupForm;
import com.example.demo.repository.AdminRepository;

@Controller
public class AdminSignupController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("adminSignupForm", new AdminSignupForm());
        return "signup";
    }

    @PostMapping("/admin/signup")
    public String registerAdmin(@ModelAttribute("adminSignupForm") AdminSignupForm adminSignupForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        Admin admin = new Admin();
        admin.setLastName(adminSignupForm.getLastName());
        admin.setFirstName(adminSignupForm.getFirstName());
        admin.setEmail(adminSignupForm.getEmail());
        admin.setPassword(passwordEncoder.encode(adminSignupForm.getPassword()));
        adminRepository.save(admin);

        return "redirect:/admin/signin";
    }
}
