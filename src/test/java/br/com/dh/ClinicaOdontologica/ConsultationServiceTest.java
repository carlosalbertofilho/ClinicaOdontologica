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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ConsultationServiceTest {

  @Autowired
  ConsultationService service;




  static Consultation consultation;
  static Client client;
  static Dentist dentist;

  @BeforeAll
  static void doBefore(){
    consultation = new Consultation();
    ClientService clientService = new ClientService();
    client = new Client();
    client.setRg("11111111-1");
    client.setName("Antonio");
    client.setLastName("Souza");
    client.setLogin("antonio.souza");
    client.setPassword("as1234");
    client.setAddress("Rua vinte, 10");
    client.setCreatedAt(LocalDate.now());
    client.setUpdateAt(LocalDate.now());
    clientService.save(ClientUtil.convertToDTO(client));

    DentistService dentistService = new DentistService();
    dentist = new Dentist();
    dentist.setRegistration("111111");
    dentist.setName("Claudio");
    dentist.setLastName("Duarte");
    dentist.setLogin("claudio.duarte");
    dentist.setPassword("cd1234");
    dentist.setIsAdmin(true);
    dentistService.save(DentistUtil.convertToDTO(dentist));

    consultation.setClient(client);
    consultation.setDentist(dentist);
    consultation.setScheduledDate(LocalDate.now());
    consultation.setScheduledTime(Time.valueOf(LocalTime.now()));


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
    ConsultationDTO consultationSave = service.save(ConsultationUtil.convertToDTO(consultation));
    List<ConsultationDTO> resultList = service.findAll();
    Assertions.assertNotNull(resultList);

  }

  @Test
  void findADentist(){
    ConsultationDTO consultationSave = service.save(ConsultationUtil.convertToDTO(consultation));
    Optional<Consultation> resultList = service.findById(1L);
    Assertions.assertNotNull(resultList);

  }
}
