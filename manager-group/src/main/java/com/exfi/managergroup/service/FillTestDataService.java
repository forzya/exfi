package com.exfi.managergroup.service;

import com.exfi.managergroup.ActivityStatus;
import com.exfi.managergroup.DirectoryGroup;
import com.exfi.managergroup.EmailGenerator;
import com.exfi.managergroup.common.schedule.SchedulerDisableRandomGroupTask;
import com.exfi.managergroup.repository.IDirectoryGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillTestDataService {

    private final Logger logger = LoggerFactory.getLogger(SchedulerDisableRandomGroupTask.class);

    private final IDirectoryGroupRepository repository;

    public FillTestDataService(IDirectoryGroupRepository repository) {
        this.repository = repository;
    }

    public void fill() {
        EmailGenerator emailGenerator = new EmailGenerator();
        List<String> emails = List.of(emailGenerator.generate());
        DirectoryGroup directoryGroup = new DirectoryGroup(ActivityStatus.ACTIVE, emails);
        repository.save(directoryGroup);
    }

}

