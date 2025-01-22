package com.election.electionbackend.repository;

import com.election.electionbackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Paginering wordt automatisch ondersteund via JpaRepository
}

