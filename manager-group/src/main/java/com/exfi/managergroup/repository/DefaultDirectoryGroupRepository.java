package com.exfi.managergroup.repository;

import com.exfi.managergroup.ActivityStatus;
import com.exfi.managergroup.DirectoryGroup;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class DefaultDirectoryGroupRepository implements IDirectoryGroupRepository {

    JpaRepo jpaRepository;

    public DefaultDirectoryGroupRepository(JpaRepo jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public DirectoryGroup save(DirectoryGroup entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public Optional<DirectoryGroup> findById(Long aLong) {
        return jpaRepository.findById(aLong);
    }

    @Override
    public List<DirectoryGroup> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        jpaRepository.deleteById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return jpaRepository.existsById(aLong);
    }

    @Override
    public List<DirectoryGroup> findByActivityStatus(ActivityStatus activityStatus) {
        return jpaRepository.findByActivityStatus(activityStatus);
    }

    @Override
    public List<DirectoryGroup> findAllById(List<Long> ids) {
        return jpaRepository.findAllById(ids);
    }
}
