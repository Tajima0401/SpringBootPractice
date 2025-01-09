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
import com.example.demo.repository.ContactRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin/signin")
    public String showLoginForm() {
        return "signin";
    }

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
        
        System.out.println("New admin registered with email: " + admin.getEmail());
        
        return "redirect:/admin/signin";
    }

    @GetMapping("/admin/contacts")
    public String showContactList(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "contacts";
    }
}
