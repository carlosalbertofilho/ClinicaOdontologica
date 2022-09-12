package br.com.dh.ClinicaOdontologica;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.dh.ClinicaOdontologica.controller.ClientController;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class ClinicaOdontologicaApplicationTests {

  @Autowired
  ClientController clientController;

	@Test
	void contextLoads()
  {
    Assertions.assertThat(clientController).isNotNull();
	}

}
