package com.exfi.manageruser.common.schedule;

import com.exfi.manageruser.service.FillTestDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerFillTask {


    private final FillTestDataService fillTestDataService;

    public SchedulerFillTask(FillTestDataService fillTestDataService) {
        this.fillTestDataService = fillTestDataService;
    }

    @Scheduled(fixedDelay = 15000, initialDelay = 5000)
    public void schedule() {
        log.info("Generate test data in database");
        fillTestDataService.fill();
    }

}
