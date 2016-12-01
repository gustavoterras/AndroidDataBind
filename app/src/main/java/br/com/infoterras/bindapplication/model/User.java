package br.com.infoterras.bindapplication.model;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Gustavo on 28/11/2016.
 */

public class User implements Serializable {

    private static final String TAG = User.class.getSimpleName();

    private String name;
    private String avatar_url;
    private String location;
    private String email;
    private String bio;
    private String public_repos;
    private String followers;
    private String following;
    private String blog;

    public User() {
    }

    public User(String name, String avatar_url, String location, String email, String bio, String public_repos, String followers, String following, String blog) {
        this.name = name;
        this.avatar_url = avatar_url;
        this.location = location;
        this.email = email;
        this.bio = bio;
        this.public_repos = public_repos;
        this.followers = followers;
        this.following = following;
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
