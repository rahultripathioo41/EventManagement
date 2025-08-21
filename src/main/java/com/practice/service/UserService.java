package com.practice.service;

import com.practice.model.User;
import com.practice.repository.UserRepository;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("USER"); // default role
        }
        return userRepository.save(user);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    
    
//    public User loginUser(String userName,String password)
//    {
//    	Optional<User> user = userRepository.findByUserName(userName);
//    	if(user==null)
//    	{
//    		return null;
//    	}
//    	if(!user.get().getPassword().equals(password))
//    	{
//    		return null;
//    	}
//    	return user.get();
//    }
}
