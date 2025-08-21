package com.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.model.Event;
import com.practice.model.RSVP;
import com.practice.repository.EventRepository;
import com.practice.repository.RsvpRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	private RsvpRepository rsvpRepository;

	public Event getEventById(int eventId) {
		// TODO Auto-generated method stub
		return eventRepository.findById(eventId).get();
	}
	
//	public void deleteById(int eventId)
//	{
//		Event event=eventRepository.findById(eventId).get();
//		List<RSVP> rsvps=rsvpRepository.findByEvent(event);
//		
//		for(RSVP rsvp:rsvps)
//		{
//			rsvpRepository.delete(rsvp);
//		}
//		
//		eventRepository.delete(event);
//	}

}
