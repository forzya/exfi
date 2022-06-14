package com.exfi.reporthandler.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "REPORT")
@NoArgsConstructor
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(updatable=false)
    @ManyToOne(optional = false)
    private DataReport dataReport;

    @Column
    private String report;


}
