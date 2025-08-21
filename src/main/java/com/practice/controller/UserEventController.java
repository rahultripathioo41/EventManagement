package com.practice.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.model.Event;
import com.practice.model.RSVP;
import com.practice.model.User;
import com.practice.repository.EventRepository;
import com.practice.repository.RsvpRepository;
import com.practice.repository.UserRepository;

@Controller
@RequestMapping("/user/events")
public class UserEventController {

    private final EventRepository eventRepository;
    private final RsvpRepository rsvpRepository;
    private final UserRepository userRepository;

    public UserEventController(EventRepository eventRepository,
                               RsvpRepository rsvpRepository,
                               UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.rsvpRepository = rsvpRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{id}/rsvp")
    public String rsvpEvent(@PathVariable int id,
                            @RequestParam String status,
                            Principal principal) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Event Id"));

        User user = userRepository.findByUserName(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User"));

        // check if already RSVP exists
        RSVP rsvp = rsvpRepository.findByUserAndEvent(user, event)
                .orElse(new RSVP());

        rsvp.setUser(user);
        rsvp.setEvent(event);
        rsvp.setStatus(status);

        rsvpRepository.save(rsvp);

        return "redirect:/user/home";
    }
}
