package com.murali.au.tutorial;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class Post {
    @Id
    private Long id;
    private String title;
    private String content;
    private String author;
    // getters and setters
}