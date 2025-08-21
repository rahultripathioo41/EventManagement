package com.practice.controller;

import com.practice.model.Event;
import com.practice.model.RSVP;
import com.practice.model.User;
import com.practice.repository.RsvpRepository;
import com.practice.service.EventService;
import com.practice.service.RsvpService;
import com.practice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class RsvpController {

    private final RsvpService rsvpService;
    private final EventService eventService;
    private final UserService userService;
    
    @Autowired
    private RsvpRepository rsvpRepository;

    public RsvpController(RsvpService rsvpService, EventService eventService, UserService userService) {
        this.rsvpService = rsvpService;
        this.eventService = eventService;
        this.userService = userService;
    }

    /**
     * ✅ Save or Update RSVP for logged-in user
     */
    @PostMapping("/user/rsvp/save")
    public String saveRSVP(@RequestParam("eventId") int eventId,
                           @RequestParam("status") String status,
                           Principal principal) {
        // Logged-in user
        User user = userService.findByUsername(principal.getName()).orElseThrow();

        // Event
        Event event = eventService.getEventById(eventId);

        // Save/Update RSVP
        rsvpService.submitRSVP(user, event, status);

        // Redirect back to event details with success message
        return "redirect:/user/home";
    }
    
    
    @GetMapping("/user/rsvpEvents")
    public String userRsvpEvents(Model model, Principal principal) {
        // Current user fetch karo
        User user = userService.findByUsername(principal.getName())
                               .orElseThrow(() -> new RuntimeException("User not found"));

        // User ke sare RSVPs le aao
        List<RSVP> rsvps = rsvpRepository.findByUser(user);

        // RSVPs se related events nikal lo
        Map<Long, String> userRsvps = new HashMap<>();
        for (RSVP r : rsvps) {
            userRsvps.put(r.getEvent().getId(), r.getStatus());
        }

        // Events list nikalo
        List<Event> events = rsvps.stream()
                                  .map(RSVP::getEvent)
                                  .toList();

        model.addAttribute("events", events);
        model.addAttribute("userRsvps", userRsvps);

        return "rsvpEvents"; // ye naya thymeleaf page hoga
    }

}
