package com.exfi.managergroup.service;

import com.exfi.managergroup.ActivityStatus;
import com.exfi.managergroup.DirectoryGroup;
import com.exfi.managergroup.repository.IDirectoryGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DisableRandomGroupService {

    private final Logger logger = LoggerFactory.getLogger(DisableRandomGroupService.class);

    private final IDirectoryGroupRepository repository;

    public DisableRandomGroupService(IDirectoryGroupRepository repository) {
        this.repository = repository;
    }

    public void disable() {
        List<DirectoryGroup> directoryGroups = repository.findByActivityStatus(ActivityStatus.ACTIVE);

        DirectoryGroup directoryGroup = getRandomDirectoryGroup(directoryGroups);
        directoryGroup.setActivityStatus(ActivityStatus.INACTIVE);

        logger.info("Disable group for DirectoryGroup with id " + directoryGroup.getId());

        repository.save(directoryGroup);
    }

    private DirectoryGroup getRandomDirectoryGroup(List<DirectoryGroup> directoryGroups) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(directoryGroups.size());

        return directoryGroups.get(randomIndex);
    }

}
