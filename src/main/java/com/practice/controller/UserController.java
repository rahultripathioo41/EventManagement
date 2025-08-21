package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.practice.model.Event;
import com.practice.repository.EventRepository;

@Controller
public class UserController {
	
	@Autowired
	private EventRepository eventRepository; 
	
	@GetMapping("/user/events/{id}")
	public String viewEvent(@PathVariable int id, Model model) {
	    Event event = eventRepository.findById(id)
	                                 .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
	    model.addAttribute("event", event);
	    return "eventDetail"; 
	}

}
