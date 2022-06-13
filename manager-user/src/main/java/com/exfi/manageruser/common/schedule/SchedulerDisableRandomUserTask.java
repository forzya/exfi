package com.exfi.manageruser.common.schedule;

import com.exfi.manageruser.service.DisableRandomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerDisableRandomUserTask {

    private final Logger logger = LoggerFactory.getLogger(SchedulerDisableRandomUserTask.class);

    private final DisableRandomUserService disableRandomGroupService;

    public SchedulerDisableRandomUserTask(DisableRandomUserService disableRandomGroupService) {
        this.disableRandomGroupService = disableRandomGroupService;
    }

    @Scheduled(fixedDelay = 15000, initialDelay = 5000)
    public void schedule() {
        logger.info("Disable random user");
        disableRandomGroupService.disable();
    }

}
