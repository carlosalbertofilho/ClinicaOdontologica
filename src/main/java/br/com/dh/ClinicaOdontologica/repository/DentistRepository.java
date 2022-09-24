package br.com.dh.ClinicaOdontologica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.ClinicaOdontologica.entity.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Long>
{
  Optional<Dentist> findByLogin(String login);
}
