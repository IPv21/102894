package com.technews;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.technews.model.Comment;
import com.technews.model.Post;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class HelloWorld{
    public static void main(String[] args){
        System.out.println("Hello World");

        int a = 4;
        double sum = 3.5;
        String c = "cat";
        String concat = a + c;
        System.out.println(sum);
        System.out.println(concat);

    }

    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Table(name = "user")
    public static class User implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
        private String username;
        @Column(unique = true)
        private String email;
        private String password;
        @Transient
        boolean loggedIn;
        @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Post> posts;
        // Need to use FetchType.LAZY to resolve multiple bags exception
        @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Vote> votes;
        // Need to use FetchType.LAZY to resolve multiple bags exception
        @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Comment> comments;

        public User() {
        }

        public User(Integer id, String username, String email, String password) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password){
            this.password = password;
        }

        public boolean isLoggedIn() {
            return LoggedIn();
        }

        public void setLoggedIn(boolean loggedIn) {
            this.loggedIn = loggedIn;
        }

        public List<Post> getPosts() {
            return posts;
        }

        public void setPosts(List<Post> posts) {
            this.posts = posts;
        }

        public List<Vote> getVotes() {
            return votes;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return loggedIn == user.loggedIn && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(posts, user.posts) && Objects.equals(votes, user.votes) && Objects.equals(comments, user.comments);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, username, email, password, loggedIn, posts, votes, comments);
        }
    }

    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Table(name = "vote")
    public static class Vote implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
        private Integer userId;
        private Integer postId;

        public Vote() {
        }

        public Vote(Integer id, Integer userId, Integer postId) {
            this.id = id;
            this.userId = userId;
            this.postId = postId;
        }

        public Vote(Integer userId, Integer postId) {
            this.userId = userId;
            this.postId = postId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getPostId() {
            return postId;
        }

        public void setPostId(Integer postId) {
            this.postId = postId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Vote)) return false;
            Vote vote = (Vote) o;
            return Objects.equals(getId(), vote.getId()) &&
                    Objects.equals(getUserId(), vote.getUserId()) &&
                    Objects.equals(getPostId(), vote.getPostId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getUserId(), getPostId());
        }

        @Override
        public String toString() {
            return "Vote{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", postId=" + postId +
                    '}';
        }
    }
}