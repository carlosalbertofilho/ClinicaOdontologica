package br.com.dh.ClinicaOdontologica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.ClinicaOdontologica.entity.Consultation;

public interface ConsultationUserRepository extends JpaRepository<Consultation, Long>
{
    
}
