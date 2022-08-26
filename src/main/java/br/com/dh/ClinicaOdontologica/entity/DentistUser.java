package br.com.dh.ClinicaOdontologica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Builder;

@Builder
@Entity
public class DentistUser extends User 
{
    @Column(name = "registration", nullable = false)
    private String registration;

    @Column(name = "isAdmin")
    private Boolean isAdminBoolean;
}
