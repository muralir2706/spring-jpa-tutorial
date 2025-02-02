package com.murali.au.tutorial.repo;

import com.murali.au.tutorial.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByTitle(String title);
    List<Post> findByAuthor(String author);
    void deleteAll();
}