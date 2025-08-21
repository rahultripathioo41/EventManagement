package com.practice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.model.RSVP;
import com.practice.model.User;
import com.practice.model.Event;
import java.util.Optional;
import java.util.List;


@Repository
public interface RsvpRepository extends CrudRepository<RSVP, Integer> {
    
   
    Optional<RSVP> findByUserAndEvent(User user, Event event);

    
    List<RSVP> findByEvent(Event event);

    
    List<RSVP> findByUser(User user);
}
