package br.com.dh.ClinicaOdontologica.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.repository.ClientRepository;
import br.com.dh.ClinicaOdontologica.repository.DentistRepository;
import br.com.dh.ClinicaOdontologica.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAuThService implements UserDetailsService {
  private UserRepository userRepository;
  private ClientRepository clientRepository;
  private DentistRepository dentistRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      UserDetails response;
      if (userRepository.findByUsername(username).isPresent())
        response = userRepository.findByUsername(username).get();
      else if (clientRepository.findByLogin(username).isPresent())
        response = clientRepository.findByLogin(username).get();
      else response = dentistRepository.findByLogin(username).get();
      return response;
    }catch (UsernameNotFoundException e){
      throw new UsernameNotFoundException("Usuario não existe");
    }
  }
}
