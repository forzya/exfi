package com.exfi.reporthandler.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DATA_REPORT")
public class DataReport {

    @Id
    @GeneratedValue
    private Long id;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "DATA_REPORT_REPORT",
//            joinColumns = @JoinColumn(name="DATA_REPORT_ID"),
//            inverseJoinColumns = @JoinColumn(name="REPORT_ID", nullable = false)
//    )
//    private List<Report> singleReports;

    public DataReport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
