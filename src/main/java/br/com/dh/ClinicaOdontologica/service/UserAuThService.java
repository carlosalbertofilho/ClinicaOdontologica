package br.com.dh.ClinicaOdontologica.service;

import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  private UserRepository repository;

  public UserAuThService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return repository.findByUsername(username);
    }catch (UsernameNotFoundException e){
      throw new UsernameNotFoundException("Usuario não existe");
    }
  }
}
