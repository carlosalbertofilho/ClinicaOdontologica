package br.com.dh.ClinicaOdontologica;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.service.ClientService;
import br.com.dh.ClinicaOdontologica.util.ClientUtil;
import br.com.dh.ClinicaOdontologica.util.DentistUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class ClientServiceTest {

  @Autowired
  ClientService service;

  static Client client;

  @BeforeAll
  static void doBefore(){
      client = new Client();
      client.setRg("11111111-1");
      client.setName("Antonio");
      client.setLastName("Souza");
      client.setLogin("antonio.souza");
      client.setPassword("as1234");
      client.getCreatedAt();
      client.getUpdateAt();
  }

  @Test
  void successSave(){
    ClientDTO clientSave = service.save(ClientUtil.convertToDTO(client));
    Assertions.assertNotNull(clientSave.getId());

  }

  @Test
  void doingAlteration(){

  }

  @Test
  void findAllClient(){
    ClientDTO clientSave = service.save(ClientUtil.convertToDTO(client));
    List<ClientDTO> resultList = service.FindAll();
    Assertions.assertNotNull(resultList);

  }

  @Test
  void findAClient(){
    ClientDTO clientSave = service.save(ClientUtil.convertToDTO(client));
    Optional<Client> resultList = service.findById(1L);
    Assertions.assertNotNull(resultList);

  }


}
