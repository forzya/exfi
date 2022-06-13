package com.exfi.manageruser.common.schedule;

import com.exfi.manageruser.service.FillTestDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerFillTask {

    private final Logger logger = LoggerFactory.getLogger(SchedulerFillTask.class);

    private final FillTestDataService fillTestDataService;

    public SchedulerFillTask(FillTestDataService fillTestDataService) {
        this.fillTestDataService = fillTestDataService;
    }

    @Scheduled(fixedDelay = 15000, initialDelay = 5000)
    public void schedule() {
        logger.info("Generate test data in database");
        fillTestDataService.fill();
    }

}
