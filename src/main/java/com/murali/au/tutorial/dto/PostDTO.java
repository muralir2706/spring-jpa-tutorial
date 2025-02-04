package com.murali.au.tutorial.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PostDTO(
        Long id,
        @NotBlank(message = "Title is mandatory")
        String title,
        @NotBlank(message = "Content is mandatory")
        String content,
        @NotBlank(message = "Author is mandatory")
        String author
) {}