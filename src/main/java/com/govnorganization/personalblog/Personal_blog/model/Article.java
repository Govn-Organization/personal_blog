package com.govnorganization.personalblog.Personal_blog.model;

import java.time.LocalDate;

/**
 * Article class - represents a blog article
 * This is just a simple container for article data (like a box to hold information)
 */
public class Article {
    // These are the properties (data) that each article has
    private String id;              // Unique identifier for the article
    private String title;           // Article title
    private String description;     // Short description
    private String content;         // Full article content
    private LocalDate publishDate;  // When the article was published

    // Empty constructor - needed for Spring to create objects
    public Article() {
    }

    // Constructor with all fields - for creating articles with all data at once
    public Article(String id, String title, String description, String content, LocalDate publishDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.publishDate = publishDate;
    }

    // Getters and Setters - these let us read and write the private fields above
    // Getter = read the value
    // Setter = write/change the value
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}






