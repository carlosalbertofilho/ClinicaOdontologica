package br.com.dh.ClinicaOdontologica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.repository.ClientRepository;

@Service
public class ClientService
{
  @Autowired
  private ClientRepository clientRepository;

  public Client save(Client client)
  {
      return clientRepository.save(client);
  }
  public List<Client> FindAll()
  {
      return clientRepository.findAll();
  }
  public Optional<Client> findById(Long id)
  {
      return clientRepository.findById(id);
  }
  public void deleteById(Long id)
  {
      clientRepository.deleteById(id);
  }
}
