package br.com.dh.ClinicaOdontologica.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dh.ClinicaOdontologica.repository.ClientRepository;
import net.minidev.json.JSONObject;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(ClientControllerTest.class)
public class ClientControllerTest
{
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;


  @MockBean
  ClientRepository clientRepository;

  @Test
  @DisplayName("Testa salvar Cliente e testa lista de cliente")
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
      .content(objectMapper.writeValueAsString(resquest))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
  }
}
