package br.com.dh.ClinicaOdontologica.controller;

import br.com.dh.ClinicaOdontologica.service.DentistService;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(DentistController.class)
public class DentistControllerTest
{
  @MockBean private DentistService dentistService;
  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("Teste para salvar dentista")
  public void itShouldSaveDentist() throws Exception
  {
    JSONObject request = new JSONObject();
    request.put("name", "Salomão");
    request.put("lastName", "Santos");
    request.put("login", "salomao@teste.com");
    request.put("password", "123456");
    request.put("registration", "123456");
    request.put("isAdmin", "true");
    request.put("createdAt", "2022-09-21");
    request.put("updateAt", "2022-09-21");

    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/dentista")
        .content(request.toString())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated());
  }
}
