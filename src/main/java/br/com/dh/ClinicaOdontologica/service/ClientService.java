package br.com.dh.ClinicaOdontologica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Role;
import br.com.dh.ClinicaOdontologica.repository.ClientRepository;
import br.com.dh.ClinicaOdontologica.util.ClientUtil;

@Service
public class ClientService implements UserDetailsService
{
  @Autowired
  private ClientRepository clientRepository;

  public ClientDTO save(@Valid ClientDTO clientDTO)
  {
      Client client = ClientUtil.convertToEntity(clientDTO);
      client.setRole(Role.ROLE_CLIENT);
      ClientDTO response = ClientUtil.convertToDTO(clientRepository.save(client));
      response.setPassword("");
      response.setRg("");
      return response;
  }
  public List<ClientDTO> FindAll()
  {
      return clientRepository.findAll()
        .stream()
        .map(ClientUtil::convertToDTO)
        .collect(Collectors.toList());
  }
  public Optional<Client> findById(Long id)
  {
      return clientRepository.findById(id);
  }
  public void deleteById(Long id)
  {
      clientRepository.deleteById(id);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return clientRepository.findByLogin(username)
      .orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "User Not Found"));
  }
}
