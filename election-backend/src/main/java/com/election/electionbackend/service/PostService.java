package com.election.electionbackend.service;

import com.election.electionbackend.model.Post;
import com.election.electionbackend.model.Reply;
import com.election.electionbackend.repository.PostRepository;
import com.election.electionbackend.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public PostService(PostRepository postRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
    }

    public Page<Post> getTopicsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return postRepository.findAll(pageable);
    }

    public List<Post> getLatestFiveTopics() {
        return postRepository.findAll(PageRequest.of(0, 5, Sort.by("createdAt").descending())).getContent();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Reply addReplyToPost(Long postId, Reply reply) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post niet gevonden"));
        reply.setPost(post);
        return replyRepository.save(reply);
    }
}