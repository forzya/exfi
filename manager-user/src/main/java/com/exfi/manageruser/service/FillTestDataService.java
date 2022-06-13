package com.exfi.manageruser.service;

import com.exfi.manageruser.ActivityStatus;
import com.exfi.manageruser.DirectoryUser;
import com.exfi.manageruser.repository.IDirectoryUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FillTestDataService {

    private final Logger logger = LoggerFactory.getLogger(FillTestDataService.class);

    private final IDirectoryUserRepository repository;

    public FillTestDataService(IDirectoryUserRepository repository) {
        this.repository = repository;
    }

    public void fill() {
        DirectoryUser directoryUser = new DirectoryUser("login", "email", ActivityStatus.ACTIVE);
        repository.save(directoryUser);
    }

}

