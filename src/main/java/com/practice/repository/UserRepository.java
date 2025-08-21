package com.practice.repository;

import org.springframework.stereotype.Repository;

import com.practice.model.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>
{
	Optional<User> findByUserName(String username);

	
}
