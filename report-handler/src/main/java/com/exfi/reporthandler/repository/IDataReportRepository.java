package com.exfi.reporthandler.repository;

import com.exfi.reporthandler.model.DataReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDataReportRepository extends JpaRepository<DataReport, Long> {
}
