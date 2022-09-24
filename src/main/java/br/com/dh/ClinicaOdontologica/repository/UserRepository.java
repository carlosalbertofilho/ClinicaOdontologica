package br.com.dh.ClinicaOdontologica.repository;

import br.com.dh.ClinicaOdontologica.entity.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAdmin, Long> {
  Optional<UserAdmin> findByUsername(String username);
}
