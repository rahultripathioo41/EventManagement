package com.practice.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer>{
	List<Event> findByDateAfterOrderByDateAsc(LocalDate date);
	
	Event findById(Long id);
}
