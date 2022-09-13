package br.com.dh.ClinicaOdontologica.service;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.util.ClientUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
      client.setAddress("Rua 1");
      client.setCreatedAt(LocalDate.now());
      client.setUpdateAt(LocalDate.now());
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
    List<ClientDTO> resultList = service.FindAll();
    Assertions.assertNotNull(resultList);

  }

  @Test
  void findAClient(){
    Optional<Client> resultList = service.findById(1L);
    Assertions.assertNotNull(resultList);

  }


}
