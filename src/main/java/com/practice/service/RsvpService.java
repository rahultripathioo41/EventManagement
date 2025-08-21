package com.practice.service;



import com.practice.model.Event;
import com.practice.model.RSVP;
import com.practice.model.User;
import com.practice.repository.RsvpRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RsvpService {

    private final RsvpRepository rsvpRepository;

    public RsvpService(RsvpRepository rsvpRepository) {
        this.rsvpRepository = rsvpRepository;
    }

    // Add or update RSVP
    public void submitRSVP(User user, Event event, String status) {
        RSVP rsvp = rsvpRepository.findByUserAndEvent(user, event).orElse(new RSVP());
        rsvp.setUser(user);
        rsvp.setEvent(event);
        rsvp.setStatus(status);
        rsvpRepository.save(rsvp);
    }

    // Get RSVP of a user for an event
    public RSVP getUserRSVP(User user, Event event) {
        return rsvpRepository.findByUserAndEvent(user, event).orElse(null);
    }

    // Get RSVP summary for admin
    public Map<String, Long> getRSVPSummary(Event event) {
        List<RSVP> rsvps = rsvpRepository.findByEvent(event);

        Map<String, Long> summary = new HashMap<>();
        summary.put("Going", rsvps.stream().filter(r -> "Going".equalsIgnoreCase(r.getStatus())).count());
        summary.put("Maybe", rsvps.stream().filter(r -> "Maybe".equalsIgnoreCase(r.getStatus())).count());
        summary.put("Decline", rsvps.stream().filter(r -> "Decline".equalsIgnoreCase(r.getStatus())).count());

        return summary;
    }
}
