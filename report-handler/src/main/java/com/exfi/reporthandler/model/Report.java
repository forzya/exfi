package com.exfi.reporthandler.model;

import javax.persistence.*;

@Entity
@Table(name = "REPORT")
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(updatable=false)
    @ManyToOne(optional = false)
    private DataReport dataReport;

    @Column
    private String report;

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public DataReport getDataReport() {
        return dataReport;
    }

    public void setDataReport(DataReport dataReport) {
        this.dataReport = dataReport;
    }

}
