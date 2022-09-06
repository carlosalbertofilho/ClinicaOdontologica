package br.com.dh.ClinicaOdontologica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.repository.ClientRepository;
import br.com.dh.ClinicaOdontologica.util.ClientUtil;

@Service
public class ClientService
{
  @Autowired
  private ClientRepository clientRepository;

  public ClientDTO save(ClientDTO clientDTO)
  {
      Client client = clientRepository
        .save(ClientUtil.convertToEntity(clientDTO));
      return ClientUtil.convertToDTO(client);
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
}
