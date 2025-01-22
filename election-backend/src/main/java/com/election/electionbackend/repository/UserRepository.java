package com.election.electionbackend.repository;

import com.election.electionbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by email
    User findByEmail(String email);

    // Find a user by name
    User findByName(String name);
}
