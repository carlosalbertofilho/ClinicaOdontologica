package br.com.dh.ClinicaOdontologica.controller;


import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.service.ClientService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest
{
  @MockBean
  private ClientService clientService;

  @Autowired
  MockMvc mockMvc;


  @Test
  @DisplayName("Teste de lista de Clientes")
  public void itShouldListClients() throws Exception
  {
    List<ClientDTO> value = new ArrayList<>();
    value.add(new ClientDTO(Long.valueOf("1")
        , "Carlos"
        , "Filho"
        , "carlos.filho@teste.com"
        , "123456"
        , "123456"
        , "Rua 01"
        , LocalDate.now()
        , LocalDate.now()));

    // MAKE A MOCK
    when(this.clientService.FindAll())
      .thenReturn(value);

    // MAKE A TEST
    mockMvc.perform( MockMvcRequestBuilders
      .get("/api/cliente")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect( MockMvcResultMatchers.status().isOk() );
  }

  @Test
  @DisplayName("Testa salvar Cliente")
  public void itShouldSaveClientAndListThen() throws Exception
  {
    JSONObject resquest = new JSONObject();
    resquest.put("name", "Drogo");
    resquest.put("lastName", "Cachorroso");
    resquest.put("login", "drogo@teste.com");
    resquest.put("password", "Teste@1234");
    resquest.put("rg", "11.222.333-6");
    resquest.put("address", "rua 10");


    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/cliente")
        .content(resquest.toString())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  @DisplayName("Testar Busca por ID")
  public void itShouldFindClientById() throws Exception
  {
    Optional<Client> value =
    Optional.of(new Client(Long.valueOf("1")
        , "Carlos"
        , "Filho"
        , "carlos.filho@teste.com"
        , "123456"
        , "123456"
        , "Rua 01"
        , LocalDate.now()
        , LocalDate.now()));

    //Make a Mock
    when(this.clientService.findById(Long.valueOf("1")))
      .thenReturn(value);

    mockMvc.perform(MockMvcRequestBuilders
      .get("/api/cliente/{id}", 1)
      .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
  }

  @Test
  @DisplayName("Retorna 'Not Found' quando não acha o ID do Cliente")
  public void itShouldNotFoundClientById() throws Exception
  {
    Optional<Client> value = Optional.empty();

    //Make a mock
    when(this.clientService.findById(Long.valueOf("1")))
      .thenReturn(value);
    //make a test
    mockMvc.perform(MockMvcRequestBuilders
      .get("/api/cliente/{id}", 1)
      .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  @DisplayName("Testa deletar um Cliente")
  public void itShouldDeleteClient() throws Exception
  {
    Client user = new Client(Long.valueOf("1")
    , "Carlos"
    , "Filho"
    , "carlos.filho@teste.com"
    , "123456"
    , "123456"
    , "Rua 01"
    , LocalDate.now()
    , LocalDate.now());

    //Make a Mock
    when(this.clientService.findById(Long.valueOf("1")))
      .thenReturn(Optional.of(user));
    this.clientService.deleteById(user.getId());

    //make a test
    mockMvc.perform(MockMvcRequestBuilders
      .delete("/api/cliente/{id}", 1))
      .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  @DisplayName("Retorna 'Not Found' quando não acha o ID do Cliente no Delete")
  public void itShoudNotFoundDeleteClientById() throws Exception
  {
    //make a Mock
    when(this.clientService.findById(Long.valueOf("1")))
      .thenReturn(Optional.empty());

    //make a test
    mockMvc.perform(MockMvcRequestBuilders
      .delete("/api/cliente/{id}", 1))
      .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  @DisplayName("Testa atualizar um cliente")
  public void itShouldUpdateClient() throws Exception
  {
    Optional<Client> value =
    Optional.of(new Client(Long.valueOf("1")
        , "Carlos"
        , "Filho"
        , "carlos.filho@teste.com"
        , "123456"
        , "123456"
        , "Rua 01"
        , LocalDate.now()
        , LocalDate.now()));

    //Make a Mock
    when(this.clientService.findById(Long.valueOf("1")))
      .thenReturn(value);

    JSONObject resquest = new JSONObject();
    resquest.put("name", "Tchotchoza");
    resquest.put("lastName", "Cachorrosa");
    resquest.put("login", "drogo@teste.com");
    resquest.put("password", "Teste@1234");
    resquest.put("rg", "66.222.333-6");
    resquest.put("address", "rua 50");

    mockMvc.perform(MockMvcRequestBuilders
        .put("/api/cliente/{id}", 1)
        .content(resquest.toString())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  @DisplayName("Retorna 'Not Found' quando não acha o ID do Cliente no Update")
  public void itShouldNotFoundUpdateClient() throws Exception
  {
    //make a Mock
    when(this.clientService.findById(Long.valueOf("1")))
      .thenReturn(Optional.empty());

      JSONObject resquest = new JSONObject();
      resquest.put("name", "Tchotchoza");
      resquest.put("lastName", "Cachorrosa");
      resquest.put("login", "drogo@teste.com");
      resquest.put("password", "Teste@1234");
      resquest.put("rg", "66.222.333-6");
      resquest.put("address", "rua 50");

      mockMvc.perform(MockMvcRequestBuilders
          .put("/api/cliente/{id}", 1)
          .content(resquest.toString())
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
