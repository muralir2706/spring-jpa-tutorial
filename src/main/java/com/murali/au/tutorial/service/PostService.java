package com.murali.au.tutorial.service;

import com.murali.au.tutorial.Post;
import com.murali.au.tutorial.dto.PostDTO;
import com.murali.au.tutorial.exception.PostNotFoundException;
import com.murali.au.tutorial.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<PostDTO> getAllPosts() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getAuthor()))
                .collect(Collectors.toList());
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.title());
        post.setContent(postDTO.content());
        post.setAuthor(postDTO.author());
        Post savedPost = postRepository.save(post);
        return new PostDTO(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), savedPost.getAuthor());
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    public Post updatePost(Long id, Post post) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setAuthor(post.getAuthor());
        return postRepository.save(existingPost);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }
}
