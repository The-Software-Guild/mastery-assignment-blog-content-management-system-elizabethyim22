package com.wiley.BlogManagementSystem.model;

import java.util.Objects;

public class User {
    private int user_id;
    private String role;
    private String username;
    private String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUser_id() == user.getUser_id() && getRole().equals(user.getRole()) && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getRole(), getUsername(), getPassword());
    }
}

