package br.com.dh.ClinicaOdontologica.util;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;

@Component
public class ClientUtil
{

  @Autowired
  private static final ModelMapper modelMap = new ModelMapper();

  public static ClientDTO convertToDTO(Client client)
  {
    return modelMap.map(client, ClientDTO.class);
  }
  public static Client convertToEntity(ClientDTO clientDTO)
  {
    return modelMap.map(clientDTO, Client.class);
  }
  public static Client clientSave(ClientDTO clientDTO)
  {
    Client client = convertToEntity(clientDTO);
    client.setCreatedAt(LocalDate.now());
    return client;
  }
}
