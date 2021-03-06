package com.exfi.managergroup.service;

import com.exfi.managergroup.ActivityStatus;
import com.exfi.managergroup.DirectoryGroup;
import com.exfi.managergroup.EmailGenerator;
import com.exfi.managergroup.repository.IDirectoryGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillTestDataService {

    private final IDirectoryGroupRepository repository;

    public FillTestDataService(IDirectoryGroupRepository repository) {
        this.repository = repository;
    }

    public void fill() {
        EmailGenerator emailGenerator = new EmailGenerator();
        List<String> emails = List.of(emailGenerator.generate());
        DirectoryGroup directoryGroup = new DirectoryGroup("1234567891",ActivityStatus.ACTIVE, emails);
        repository.save(directoryGroup);
    }

}

