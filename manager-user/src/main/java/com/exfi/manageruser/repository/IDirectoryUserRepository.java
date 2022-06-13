package com.exfi.manageruser.repository;

import com.exfi.manageruser.ActivityStatus;
import com.exfi.manageruser.DirectoryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDirectoryUserRepository extends JpaRepository<DirectoryUser, Long> {

    List<DirectoryUser> findByActivityStatus(ActivityStatus activityStatus);

}
