package com.exfi.manageruser;

import javax.persistence.*;

@Entity
@Table(name = "DIRECTORY_USER")
public class DirectoryUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String login;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;

    public DirectoryUser() {
    }

    public DirectoryUser(String login, String email, ActivityStatus activityStatus) {
        this.login = login;
        this.email = email;
        this.activityStatus = activityStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }
}
