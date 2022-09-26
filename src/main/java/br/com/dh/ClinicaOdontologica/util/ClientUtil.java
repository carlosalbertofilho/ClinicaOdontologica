package br.com.dh.ClinicaOdontologica.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.dto.ClientResponseDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;

@Component
public class ClientUtil
{

  private static final ModelMapper MODEL_MAPPER = new ModelMapper();

  public static ClientDTO convertToDTO(Client client)
  {
    return MODEL_MAPPER.map(client, ClientDTO.class);
  }
  public static Client convertToEntity(ClientDTO clientDTO)
  {
    return MODEL_MAPPER.map(clientDTO, Client.class);
  }
  public static ClientResponseDTO convertToResponse(Client client)
  {
    return MODEL_MAPPER.map(client, ClientResponseDTO.class);
  }
}
