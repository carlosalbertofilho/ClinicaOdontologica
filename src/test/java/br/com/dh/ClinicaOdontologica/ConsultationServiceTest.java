package br.com.dh.ClinicaOdontologica;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.service.ClientService;
import br.com.dh.ClinicaOdontologica.service.ConsultationService;
import br.com.dh.ClinicaOdontologica.service.DentistService;
import br.com.dh.ClinicaOdontologica.util.ClientUtil;
import br.com.dh.ClinicaOdontologica.util.ConsultationUtil;
import br.com.dh.ClinicaOdontologica.util.DentistUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConsultationServiceTest {

  @Autowired
  ConsultationService service;

  @Autowired
  DentistService dentistService;

  @Autowired
  ClientService clientService;

  Consultation consultation;
  Client client;
  Dentist dentist;


  @BeforeAll
  void doBefore(){
    consultation = new Consultation();
    this.client = new Client();
    this.client.setRg("11111111-1");
    this.client.setName("Antonio");
    this.client.setLastName("Souza");
    this.client.setLogin("antonio.souza");
    this.client.setPassword("as1234");
    this.client.setAddress("Rua vinte, 10");
    this.client.setCreatedAt(LocalDate.now());
    this.client.setUpdateAt(LocalDate.now());
    clientService.save(ClientUtil.convertToDTO(client));

    this.dentist = new Dentist();
    this.dentist.setRegistration("111111");
    this.dentist.setName("Claudio");
    this.dentist.setLastName("Duarte");
    this.dentist.setLogin("claudio.duarte");
    this.dentist.setPassword("cd1234");
    this.dentist.setIsAdmin(true);
    this.dentist.setCreatedAt(LocalDate.now());
    this.dentist.setUpdateAt(LocalDate.now());
    dentistService.save(DentistUtil.convertToDTO(dentist));

    consultation.setClient(client);
    consultation.setDentist(dentist);
    consultation.setScheduledDate(LocalDate.now());
    consultation.setScheduledTime(Time.valueOf(LocalTime.now()));
    consultation.setCreatedAt(LocalDate.now());
    consultation.setUpdateAt(LocalDate.now());


  }

  @Test
  void successSave(){
    ConsultationDTO consultationSave = service.save(ConsultationUtil.convertToDTO(consultation));
    Assertions.assertNotNull(consultationSave.getId());

  }

  @Test
  void doingAlteration(){

  }

  @Test
  void findAllDentist(){
    List<ConsultationDTO> resultList = service.findAll();
    Assertions.assertNotNull(resultList);

  }

  @Test
  void findADentist(){
    Optional<Consultation> resultList = service.findById(1L);
    Assertions.assertNotNull(resultList);

  }
}
