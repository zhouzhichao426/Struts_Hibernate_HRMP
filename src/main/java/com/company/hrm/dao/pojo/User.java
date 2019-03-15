package com.company.hrm.dao.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    private int id;
    private String username;
    private String userpassword;
    private Integer priority;

    public User() {
    }

    public User(int id, String username, String userpassword, Integer priority) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.priority = priority;
    }

    public User(String username, String userpassword) {
        this.username = username;
        this.userpassword = userpassword;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "userpassword")
    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Basic
    @Column(name = "priority")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(userpassword, user.userpassword) &&
                Objects.equals(priority, user.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, userpassword, priority);
    }
}
