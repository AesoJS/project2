package com.election.electionbackend.controller;

import com.election.electionbackend.model.Post;
import com.election.electionbackend.model.Reply;
import com.election.electionbackend.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/topics")
    public Page<Post> getTopicsWithPagination(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size) {
        return postService.getTopicsWithPagination(page, size);
    }

    @GetMapping("/topics/latest")
    public List<Post> getLatestFiveTopics() {
        return postService.getLatestFiveTopics();
    }

    @PostMapping("/topics")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(201).body(createdPost);
    }

    @PostMapping("/topics/{postId}/replies")
    public ResponseEntity<Reply> addReply(@PathVariable Long postId, @RequestBody Reply reply) {
        Reply createdReply = postService.addReplyToPost(postId, reply);
        return ResponseEntity.status(201).body(createdReply);
    }
}
