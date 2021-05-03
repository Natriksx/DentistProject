package com.cgi.dentistapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "appointment")
public class DentistVisitEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dentist_name")
    private String dentistName;

    @Column(name = "visit_date")
    private Date visitDate;

    @Column(name = "visit_time")
    private String visitTime;
}
