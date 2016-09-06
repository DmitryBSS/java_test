package ru.stqa.pft.mantis.model;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "mantis_user_table")
public class UserData {
    @Id
    @Column(name = "id")
    @Type(type = "int")
    private int id;

    @Column(name = "username")
    @Type(type = "string")
    private String username;

    @Column(name = "email")
    @Type(type = "string")
    private String email;

    public UserData(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserData() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
