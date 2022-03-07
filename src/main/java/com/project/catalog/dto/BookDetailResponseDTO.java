package com.project.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDetailResponseDTO {

    private String title;

    @JsonProperty("author_name")
    private String author;

    private Long authorId;
    private String description;

    public String getTitle() {
        return title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
