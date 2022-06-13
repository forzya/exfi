package com.exfi.managergroup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DIRECTORY_GROUP")
@NoArgsConstructor
@Getter
@Setter
public class DirectoryGroup {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 10)
    @Size(min = 10,max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String identifier;

    @Column
    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;

    @ElementCollection()
    @CollectionTable(name = "EMAILS", joinColumns = @JoinColumn(name = "EMAIL_ID"))
    private List<String> emails;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public DirectoryGroup(String identifier, ActivityStatus activityStatus, List<String> emails) {
        this.identifier = identifier;
        this.activityStatus = activityStatus;
        this.emails = emails;
    }
}
