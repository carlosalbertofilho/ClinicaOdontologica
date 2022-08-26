package br.com.dh.ClinicaOdontologica.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dh.ClinicaOdontologica.repository.DentistRepository;

public class DentistService 
{
    @Autowired
    private DentistRepository dentistUserRepository;    
}
