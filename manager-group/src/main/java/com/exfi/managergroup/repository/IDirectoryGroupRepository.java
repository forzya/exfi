package com.exfi.managergroup.repository;

import com.exfi.managergroup.ActivityStatus;
import com.exfi.managergroup.DirectoryGroup;

import java.util.List;
import java.util.Optional;


public interface IDirectoryGroupRepository {

    public DirectoryGroup save(DirectoryGroup entity);

    public Optional<DirectoryGroup> findById(Long aLong);

    public List<DirectoryGroup> findAll();

    public void deleteById(Long aLong);

    public boolean existsById(Long aLong);

    List<DirectoryGroup> findByActivityStatus(ActivityStatus status);

    List<DirectoryGroup> findAllById(List<Long> ids);

}
