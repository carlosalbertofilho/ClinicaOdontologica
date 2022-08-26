package br.com.dh.ClinicaOdontologica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.ClinicaOdontologica.entity.ClientUser;

public interface ClientUserRepository extends JpaRepository<ClientUser, Long>
{
    
}
