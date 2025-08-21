package com.practice.controller;


import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.practice.model.Event;
import com.practice.model.RSVP;
import com.practice.model.User;
import com.practice.repository.EventRepository;
import com.practice.repository.RsvpRepository;
import com.practice.repository.UserRepository;
import com.practice.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RsvpRepository rsvpRepository;
	 

    @GetMapping("/admin/home")
    public String adminHome(Model model) {
    	model.addAttribute("events", eventRepository.findAll());
        return "adminHome";  // admin ka view
    }

    @GetMapping("/user/home")
    public String userHome(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        List<Event> events = eventRepository.findByDateAfterOrderByDateAsc(LocalDate.now());

        // Map: EventId -> Status
        Map<Long, String> userRsvps = new HashMap<>();
        for (Event e : events) {
            RSVP rsvp = rsvpRepository.findByUserAndEvent(user, e).orElse(null);
            if (rsvp != null) {
                userRsvps.put(e.getId(), rsvp.getStatus());
            }
        }

        model.addAttribute("events", events);
        model.addAttribute("userRsvps", userRsvps);

        return "userHome";
    }
}
