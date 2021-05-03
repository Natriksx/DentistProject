package com.cgi.dentistapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

@Setter
@Getter
public class DentistVisitDTO {

    private Long id;

    @NotNull
    private String dentistName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date visitDate;

    @NotNull
    private String visitTime;

}
