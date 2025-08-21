package com.practice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.practice.model.Event;
import com.practice.service.EventService;
import com.practice.service.RsvpService;

@Controller
public class AdminRsvpController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private RsvpService rsvpService;
	
	@GetMapping("admin/rsvpSummary/{eventId}")
    public String viewSummary(@PathVariable int eventId, Model model) {
        Event event = eventService.getEventById(eventId);
        Map<String, Long> summary = rsvpService.getRSVPSummary(event);

        model.addAttribute("event", event);
        model.addAttribute("summary", summary);

        return "rsvpSummary";  
    }
}
