package br.com.dh.ClinicaOdontologica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.repository.UserRepository;

@Service
public class UserAuThService implements UserDetailsService {
  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return repository.findByUsername(username).get();
    }catch (UsernameNotFoundException e){
      throw new UsernameNotFoundException("Usuario não existe");
    }
  }
}
