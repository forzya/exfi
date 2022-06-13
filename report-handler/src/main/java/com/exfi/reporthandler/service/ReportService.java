package com.exfi.reporthandler.service;

import com.exfi.reporthandler.Constant;
import com.exfi.reporthandler.model.DataReport;
import com.exfi.reporthandler.model.Report;
import com.exfi.reporthandler.repository.IDataReportRepository;
import com.exfi.reporthandler.repository.IReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService implements IReportService {

    private final IReportRepository reportRepository;
    private final IDataReportRepository dataReportRepository;
    private final KeycloakRestTemplate template;
    private final MailNotifier mailNotifier;
    private final ObjectMapper objectMapper;


    public ReportService(IReportRepository reportRepository, IDataReportRepository dataReportRepository,
                         KeycloakRestTemplate template, MailNotifier mailNotifier) {
        this.reportRepository = reportRepository;
        this.dataReportRepository = dataReportRepository;
        this.template = template;
        this.mailNotifier = mailNotifier;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void uploadHandler(String data, List<Long> groupIds, Long id) {
        uploadReport(data, id);

        String ids = groupIds.stream().map(Object::toString).collect(Collectors.joining(","));


        String gatewayUrl = Constant.GATEWAY_URL;
        String endpoint = gatewayUrl + "manager/getGroups?ids=" + ids;
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);

        List<Map<String, Object>> groups = convertJsonToMap(response.getBody());
        List<String> emails = collectMails(groups);

        mailNotifier.sendEmail(emails);
    }

    private void uploadReport(String data, Long id) {
        DataReport dataReport;
        if (dataReportRepository.existsById(id)) {
            dataReport = dataReportRepository.findById(id).get();
        } else {
            dataReport = new DataReport(id);
            dataReportRepository.save(dataReport);
        }

        Report report = new Report();
        report.setDataReport(dataReport);
        report.setReport(data);

        reportRepository.save(report);
    }

    private List<String> collectMails(List<Map<String, Object>> groups) {
        List<String> emails = new ArrayList<>();
        groups.forEach(group -> emails.addAll((List<String>) group.get("emails")));
        return emails;
    }

    private List<Map<String, Object>> convertJsonToMap(String json) {
        try {
            return objectMapper.readValue(json, List.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
