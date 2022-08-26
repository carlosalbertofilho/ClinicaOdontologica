package br.com.dh.ClinicaOdontologica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.ClinicaOdontologica.entity.DentistUser;

public interface DentistUserRepository extends JpaRepository<DentistUser, Long>
{
    
}