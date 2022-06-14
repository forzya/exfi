package com.exfi.manageruser.common.schedule;

import com.exfi.manageruser.service.DisableRandomUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerDisableRandomUserTask {

    private final DisableRandomUserService disableRandomGroupService;

    public SchedulerDisableRandomUserTask(DisableRandomUserService disableRandomGroupService) {
        this.disableRandomGroupService = disableRandomGroupService;
    }

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void schedule() {
        log.info("Disable random user");
        disableRandomGroupService.disable();
    }

}
