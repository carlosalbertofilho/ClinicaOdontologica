package br.com.dh.ClinicaOdontologica.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class UserAuThService implements UserDetailsService {
  private final UserRepository repository;

  public UserAuThService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
  }
}
