package com.exfi.managergroup;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DIRECTORY_GROUP")
public class DirectoryGroup {

    @Id
    @GeneratedValue
    private Long id;

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

    public DirectoryGroup() {  }

    public DirectoryGroup(ActivityStatus activityStatus, List<String> emails) {
        this.activityStatus = activityStatus;
        this.emails = emails;
    }

    public Long getId() {
        return id;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public List<String> getEmails() {
        return emails;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
