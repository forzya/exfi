package com.exfi.reporthandler.model;

import javax.persistence.*;

@Entity
@Table(name = "REPORT")
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(insertable=false, updatable=false)
    @ManyToOne(optional = false)
    private DataReport dataReport;

    @Column
    private String report;

    @Column
    private User uploaded;

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

    public User getUploaded() {
        return uploaded;
    }

    public void setUploaded(User uploaded) {
        this.uploaded = uploaded;
    }

    public DataReport getDataReport() {
        return dataReport;
    }

    public void setDataReport(DataReport dataReport) {
        this.dataReport = dataReport;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", dataReport=" + dataReport +
                ", report='" + report + '\'' +
                ", uploaded=" + uploaded +
                '}';
    }
}
