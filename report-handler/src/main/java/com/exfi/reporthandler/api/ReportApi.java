package com.exfi.reporthandler.api;

import com.exfi.reporthandler.model.DataReport;
import com.exfi.reporthandler.model.Report;
import com.exfi.reporthandler.repository.IDataReportRepository;
import com.exfi.reporthandler.repository.IReportRepository;
import com.exfi.reporthandler.service.IReportService;
import com.exfi.reporthandler.validation.IValidationLayer;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/report")
public class ReportApi {

    private final IReportRepository reportRepository;
    private final IDataReportRepository dataReportRepository;
    private final IValidationLayer validationLayer;
    private final IReportService reportService;
    private final SimpMessagingTemplate simpMessagingTemplate;


    public ReportApi(IReportRepository reportRepository, IDataReportRepository dataReportRepository,
                     IValidationLayer validationLayer, IReportService reportService, SimpMessagingTemplate simpMessagingTemplate) {
        this.reportRepository = reportRepository;
        this.dataReportRepository = dataReportRepository;
        this.validationLayer = validationLayer;
        this.reportService = reportService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/getReportByEntityId")
    ResponseEntity<List<Report>> getGroups(@RequestParam Long id) {

        Optional<DataReport> dataReportOptional = dataReportRepository.findById(id);
        if (dataReportOptional.isPresent()) {
            List<Report> reports = reportRepository.findAllByDataReport(dataReportOptional.get());
            if (!reports.isEmpty()) {
                return new ResponseEntity<>(reports, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // todo always false ?
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/uploadReport")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Void> uploadReport(@RequestBody String report, @RequestParam List<Long> ids, @RequestParam Long id) {

        validationLayer.userIsValid();
        validationLayer.groupIdsIsValid(ids);

        reportService.uploadHandler(report, ids, id);
//        simpMessagingTemplate.convertAndSend("1");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/uploadReportWithoutValidation")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Void> uploadReportWithoutValidation(@RequestBody String report, @RequestParam List<Long> ids, @RequestParam Long id) {
        reportService.uploadHandler(report, ids, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
