package br.com.dh.ClinicaOdontologica;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dh.ClinicaOdontologica.controller.ClientController;
import br.com.dh.ClinicaOdontologica.controller.ConsultationController;
import br.com.dh.ClinicaOdontologica.controller.DentistController;


@SpringBootTest
class ClinicaOdontologicaApplicationTests
{

  @Autowired
  ClientController clientController;

  @Autowired
  DentistController dentistController;

  @Autowired
  ConsultationController consultationController;

	@Test
	void contextLoads()
  {
    Assertions.assertThat(clientController).isNotNull();
    Assertions.assertThat(dentistController).isNotNull();
    Assertions.assertThat(consultationController).isNotNull();
	}

}
