package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.ContactRepository;

@Controller
public class AdminContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/admin/contacts")
    public String showContactList(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "contacts";
    }
}
