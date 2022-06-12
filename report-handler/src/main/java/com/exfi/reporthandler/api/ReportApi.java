package com.exfi.reporthandler.api;

import com.exfi.reporthandler.repository.IDataReportRepository;
import com.exfi.reporthandler.repository.IReportRepository;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/report")
public class ReportApi {

    private final IReportRepository reportRepository;
    private final IDataReportRepository dataReportRepository;
    private final KeycloakRestTemplate template;

    public ReportApi(IReportRepository reportRepository, IDataReportRepository dataReportRepository, KeycloakRestTemplate template) {
        this.reportRepository = reportRepository;
        this.dataReportRepository = dataReportRepository;
        this.template = template;
    }

    @GetMapping("/upload")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<String> upload() {
        System.out.println("upload");
        String endpoint = "http://localhost:8060/manager/testme";
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        System.out.println("test_" + response.getBody());

        return new ResponseEntity<>("hellom", HttpStatus.OK);
    }


    @GetMapping("/upload0")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> upload0() {
        System.out.println("upload0");

        return new ResponseEntity<>("hellom", HttpStatus.OK);
    }


    @GetMapping("/upload1")
    ResponseEntity<String> upload1() {
        System.out.println("upload1");

        return new ResponseEntity<>("hellom", HttpStatus.OK);
    }

    @GetMapping("/upload2")
    ResponseEntity<String> upload2() {
        System.out.println("upload2");

        return new ResponseEntity<>("hellom", HttpStatus.OK);
    }

//    @GetMapping("/upload2")
//    ResponseEntity<String> upload2() {
//        System.out.println("upload2");
//        String endpoint = "http://localhost:8060/manager/getTest1";
////        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
////        System.out.println(response.getBody());
//
//        Report dataReport = getDataReport();
//        reportRepository.save(dataReport);
//
//        return new ResponseEntity<>("hellom", HttpStatus.OK);
//    }
//
//    @GetMapping("/upload3")
//    ResponseEntity<String> upload3(@RequestParam Long id) {
//        System.out.println("upload3");
//
//        DataReport dataReport = dataReportRepository.findById(id).get();
//
//        List<Report> reports = reportRepository.findAllByDataReport(dataReport);
//
//        System.out.println(reports);
//
//        return new ResponseEntity<>("hellom", HttpStatus.OK);
//    }
//
//    private Report getDataReport() {
//
//        DataReport dataReport = new DataReport();
//
//        User user = new User();
//        user.setLastName("ln");
//        user.setFirstName("fn");
//        user.setLogin("l");
//
//        Report report = new Report();
//        report.setReport("reporttestme");
//        report.setUploaded(user);
//
//        report.setDataReport(dataReport);
//
//        return report;
//    }

}
