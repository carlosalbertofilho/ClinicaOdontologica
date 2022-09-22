package br.com.dh.ClinicaOdontologica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.ClinicaOdontologica.entity.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>
{
  Optional<Client> findByLogin(String login);

}
