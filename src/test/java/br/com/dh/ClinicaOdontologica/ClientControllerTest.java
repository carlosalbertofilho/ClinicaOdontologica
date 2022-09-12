package br.com.dh.ClinicaOdontologica;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dh.ClinicaOdontologica.controller.ClientController;
import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.service.ClientService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest
{

  @TestConfiguration
  static class ClientControllerTestConfiguration
  {
    @Bean
    public ClientService clientService(){
      return new ClientService();
    }
  }

  @Autowired
  ClientService clientService;

  @Mock
  ClientDTO clientDTO;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("Testa salvar Cliente e testa lista de cliente")
  public void itShouldSaveClientAndListThen() throws Exception
  {
    clientDTO.setName("Carlos");
    clientDTO.setLastName("Alberto");
    clientDTO.setLogin("carlos.filho@teste.com");
    clientDTO.setPassword("123456");
    clientDTO.setRg("12.123.123-22");
    clientDTO.setAddress("Rua 01");


    mockMvc.perform(MockMvcRequestBuilders
      .post("/api/cliente")
      .content(objectMapper.writeValueAsString(clientDTO))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
  }
}
