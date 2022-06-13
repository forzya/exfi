package com.exfi.reporthandler.service;

import java.util.List;

public interface IReportService {

    void uploadHandler(String report, List<Long> ids, Long id);

}
