package com.exfi.reporthandler.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DATA_REPORT")
@NoArgsConstructor
@Getter
@Setter
public class DataReport {

    @Id
    private Long id;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "DATA_REPORT_REPORT",
//            joinColumns = @JoinColumn(name="DATA_REPORT_ID"),
//            inverseJoinColumns = @JoinColumn(name="REPORT_ID", nullable = false)
//    )
//    private List<Report> singleReports;


    public DataReport(Long id) {
        this.id = id;
    }
}
