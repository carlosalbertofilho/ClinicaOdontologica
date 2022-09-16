package br.com.dh.ClinicaOdontologica.controller;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.service.ClientService;
import io.restassured.http.ContentType;


@WebMvcTest(ClientController.class)
public class ClientControllerTest
{
  @Autowired
  private ClientController clientController;

  @MockBean
  private ClientService clientService;

  @MockBean
  private ConsultationController consultationController;

  @BeforeEach
  public void setup()
  {
    standaloneSetup(this.clientController);
  }

  @Test
  @DisplayName("Testa listar Clientes")
  public void itShouldListClients()
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
    given()
      .accept(ContentType.JSON)
    .when()
      .get("/api/cliente")
    .then()
      .statusCode(HttpStatus.OK.value());
  }

}

