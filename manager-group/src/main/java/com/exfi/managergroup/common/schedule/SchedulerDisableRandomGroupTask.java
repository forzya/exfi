package com.exfi.managergroup.common.schedule;

import com.exfi.managergroup.service.DisableRandomGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerDisableRandomGroupTask {

    private final DisableRandomGroupService disableRandomGroupService;

    public SchedulerDisableRandomGroupTask(DisableRandomGroupService disableRandomGroupService) {
        this.disableRandomGroupService = disableRandomGroupService;
    }

    @Scheduled(fixedDelay = 15000, initialDelay = 5000)
    public void schedule() {
        log.info("Disable random group");
        disableRandomGroupService.disable();
    }

}
