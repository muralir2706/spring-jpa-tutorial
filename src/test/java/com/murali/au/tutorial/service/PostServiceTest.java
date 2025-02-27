package com.murali.au.tutorial.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.murali.au.tutorial.Post;
import com.murali.au.tutorial.dto.PostDTO;
import com.murali.au.tutorial.exception.PostNotFoundException;
import com.murali.au.tutorial.repo.PostRepository;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPosts() {
        Post post = new Post();
        post.setId(1l);
        post.setTitle("Title");
        post.setContent("Content");
        post.setAuthor("Author");
        when(postRepository.findAll()).thenReturn(List.of(post));

        List<PostDTO> allPosts = postService.getAllPosts();
        assertNotNull(allPosts);
        assertEquals( 1, allPosts.size());
        assertEquals("Title", allPosts.getFirst().title(),"Expected title not returned");
    }

    @Test
    void testCreatePost() {
        PostDTO postDTO = new PostDTO(null, "Title", "Content", "Author");
        Post post = new Post();
        post.setTitle("Title");
        post.setContent("Content");
        post.setAuthor("Author");

        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostDTO createdPost = postService.createPost(postDTO);

        assertNotNull(createdPost);
        assertEquals("Title", createdPost.title());
    }

    @Test
    void testGetPostById_Success() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Title");
        post.setContent("Content");
        post.setAuthor("Author");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        Post foundPost = postService.getPostById(1L);

        assertNotNull(foundPost);
        assertEquals("Title", foundPost.getTitle());
    }

    @Test
    void testGetPostById_NotFound() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.getPostById(1L));
    }

    @Test
    void testUpdatePost_Success() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Title");
        post.setContent("Content");
        post.setAuthor("Author");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post updatedPost = postService.updatePost(1L, post);

        assertNotNull(updatedPost);
        assertEquals("Title", updatedPost.getTitle());
    }

    @Test
    void testUpdatePost_NotFound() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Title");
        post.setContent("Content");
        post.setAuthor("Author");

        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.updatePost(1L, post));
    }

    @Test
    void testDeletePost_Success() {
        when(postRepository.existsById(1L)).thenReturn(true);

        postService.deletePost(1L);

        verify(postRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePost_NotFound() {
        when(postRepository.existsById(1L)).thenReturn(false);

        assertThrows(PostNotFoundException.class, () -> postService.deletePost(1L));
    }
}