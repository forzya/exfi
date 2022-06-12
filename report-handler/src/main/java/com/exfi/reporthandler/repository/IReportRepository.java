package com.exfi.reporthandler.repository;

import com.exfi.reporthandler.model.DataReport;
import com.exfi.reporthandler.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByDataReport(DataReport dataReport);

}
