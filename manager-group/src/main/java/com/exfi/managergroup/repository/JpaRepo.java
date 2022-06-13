package com.exfi.managergroup.repository;

import com.exfi.managergroup.ActivityStatus;
import com.exfi.managergroup.DirectoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRepo extends JpaRepository<DirectoryGroup, Long> {

    List<DirectoryGroup> findByActivityStatus(ActivityStatus activityStatus);

}
