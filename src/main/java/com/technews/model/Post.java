package com.technews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;

//import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "post")
public class Post implements Serializable {
    private Object userId = null;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String postUrl;
    @Transient
    private String userName;
    @Transient
    private int voteCount;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "posted_at")
    private Date postedAt = new Date();
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date updatedAt = new Date();
    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
    private Object updateAt;

    public Post(Object userId) {
        this.userId = userId;
    }

    public Post(Integer id, String title, String postUrl, int voteCount){
        this.id = id;
        this.title = title;
        this.postUrl = postUrl;
        this.voteCount = voteCount;
        this.userId = userId;
    }

    public Post() {
        
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setPostUrl(String postUrl){
        this.postUrl = postUrl;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public int getVoteCount(){
        return voteCount;
    }
    public void setVoteCount(int voteCount){
     this.voteCount = voteCount;
    }
    public Integer getUserId(Integer userId){
        return userId;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void getUpdatedAt(Date updatedAt){
        this.updateAt = updateAt;
    }
    public void setUpdateAt(Date updatedAt){
        this.updatedAt = updatedAt;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public String getPostUrl() {
        return postUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return voteCount == post.voteCount && Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(postUrl, post.postUrl) && Objects.equals(userName, post.userName) && Objects.equals(postedAt, post.postedAt) && Objects.equals(updatedAt, post.updatedAt) && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, postUrl, userName, voteCount, postedAt, updatedAt, comments);
    }
}
