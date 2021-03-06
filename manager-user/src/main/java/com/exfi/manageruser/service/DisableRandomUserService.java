package com.exfi.manageruser.service;

import com.exfi.manageruser.ActivityStatus;
import com.exfi.manageruser.DirectoryUser;
import com.exfi.manageruser.repository.IDirectoryUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class DisableRandomUserService {

    private final IDirectoryUserRepository repository;


    public DisableRandomUserService(IDirectoryUserRepository directoryUserRepository) {
        this.repository = directoryUserRepository;
    }

    //todo possible need check on empty //bound must be positive
    public void disable() {
        List<DirectoryUser> directoryGroups = repository.findByActivityStatus(ActivityStatus.ACTIVE);

        DirectoryUser directoryGroup = getRandomDirectoryGroup(directoryGroups);
        directoryGroup.setActivityStatus(ActivityStatus.INACTIVE);

        log.info("Disable group for DirectoryUser with id " + directoryGroup.getId());

        repository.save(directoryGroup);
    }

    private DirectoryUser getRandomDirectoryGroup(List<DirectoryUser> directoryGroups) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(directoryGroups.size());

        return directoryGroups.get(randomIndex);
    }

}
