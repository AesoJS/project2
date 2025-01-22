package com.election.electionbackend.service;

import com.election.electionbackend.model.User;
import com.election.electionbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save a new user or update an existing one
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Find a user by name
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        // Return user details with roles (if any)
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // Note: Password should be hashed and checked
                .roles(user.getRole()) // Assuming role is a string, adapt as needed
                .build();
    }
}
