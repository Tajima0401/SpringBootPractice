package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Controller
public class AdminContactDetailController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/admin/contacts/{id}")
    public String showContactDetail(@PathVariable("id") Long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact ID"));
        model.addAttribute("contact", contact);
        return "contact_detail";
    }
}
