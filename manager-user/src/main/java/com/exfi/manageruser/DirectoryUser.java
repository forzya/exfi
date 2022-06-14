package com.exfi.manageruser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DIRECTORY_USER")
@NoArgsConstructor
@Getter
@Setter
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


    public DirectoryUser(String login, String email, ActivityStatus activityStatus) {
        this.login = login;
        this.email = email;
        this.activityStatus = activityStatus;
    }

}
