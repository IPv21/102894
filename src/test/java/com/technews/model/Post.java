package com.technews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
    private Integer id;
    private String title;
    private String postUrl;
    private String userName;
    private int voteCount:
    private Date postedAt = new Date();
    private Date updatedAt = new Date();
    private List<Comment> comments;


}
