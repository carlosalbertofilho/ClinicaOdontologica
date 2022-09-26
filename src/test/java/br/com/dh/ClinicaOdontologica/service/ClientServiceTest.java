package br.com.dh.ClinicaOdontologica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dh.ClinicaOdontologica.dto.ClientResponseDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.util.ClientUtil;


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
      client.setAddress("Rua 1");
      client.setCreatedAt(LocalDate.now());
      client.setUpdateAt(LocalDate.now());
  }

  @Test
  void successSave(){
    ClientResponseDTO clientSave = service.save(ClientUtil.convertToDTO(client));
    Assertions.assertNotNull(clientSave.getId());

  }

  @Test
  void doingAlteration(){

  }

  @Test
  void findAllClient(){
    List<ClientResponseDTO> resultList = service.FindAll();
    Assertions.assertNotNull(resultList);

  }

  @Test
  void findAClient(){
    Optional<Client> resultList = service.findById(1L);
    Assertions.assertNotNull(resultList);

  }


}
