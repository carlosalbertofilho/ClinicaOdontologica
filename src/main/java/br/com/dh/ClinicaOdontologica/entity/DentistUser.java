package br.com.dh.ClinicaOdontologica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Builder;

@Builder
@Entity
public class DentistUser extends User 
{
    @Column(nullable = false)
    private String registration;

    @Column(nullable = false)
    private Boolean isAdmin;
}
