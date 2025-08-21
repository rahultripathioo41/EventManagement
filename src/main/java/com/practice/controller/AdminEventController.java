package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.model.Event;
import com.practice.repository.EventRepository;

@Controller
@RequestMapping("/admin/events")
public class AdminEventController {

    private final EventRepository eventRepository;

    public AdminEventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // All events
    @GetMapping
    public String listEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "adminHome"; // tumhara template
    }

    // Create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        return "eventForm";  // naya form page
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute Event event) {
        eventRepository.save(event);
        return "redirect:/admin/events";
    }

    // Edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        model.addAttribute("event", event);
        return "eventForm";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable int id) {
        eventRepository.deleteById(id);
        return "redirect:/admin/events";
    }

    // RSVP Summary
    @GetMapping("/rsvps/{id}")
    public String viewRSVPs(@PathVariable int id, Model model) {
        Event event = eventRepository.findById(id).orElseThrow();
        model.addAttribute("event", event);
         
        return "rsvpSummary";
    }
    
    
}
