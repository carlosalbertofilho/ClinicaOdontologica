package br.com.dh.ClinicaOdontologica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.dh.ClinicaOdontologica.entity.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Long>
{
//  @Query("FROM Dentist AS d WHERE d.login = 1")
  Optional<Dentist> findByLogin(String login);
}
