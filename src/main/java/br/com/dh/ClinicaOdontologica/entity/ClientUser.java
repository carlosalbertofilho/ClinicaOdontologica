package br.com.dh.ClinicaOdontologica.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Builder;

@Builder
@Entity
public class ClientUser extends User 
{
    @Column(name = "rg", nullable = false)
    private String rg;

    @Column(name = "address")
    private String address;

    @Column(name = "medical_discharge_date")
    private LocalDate medicalDischargeDate;
}
