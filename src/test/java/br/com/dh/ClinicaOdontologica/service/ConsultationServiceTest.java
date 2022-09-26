package br.com.dh.ClinicaOdontologica.service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
import br.com.dh.ClinicaOdontologica.entity.Consultation;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConsultationServiceTest {

  @Autowired private ConsultationService service;

  private ConsultationDTO consultation;


  @BeforeAll
  void setup(){
    consultation = ConsultationDTO.builder()
      .id(Long.valueOf("10"))
      .clientID(Long.valueOf("1"))
      .dentistID(Long.valueOf("1"))
      .scheduledDate(LocalDate.of(2022, 9, 27))
      .scheduledTime(Time.valueOf("10:00:00"))
      .createdAt(LocalDate.now())
      .updateAt(LocalDate.now())
      .build();


  }

  @Test
  void successSave(){
    ConsultationDTO consultationSave = service.save(consultation);
    Assertions.assertNotNull(consultationSave.getId());

  }

  @Test
  void doingAlteration(){
    consultation.setScheduledTime(Time.valueOf("15:30:00"));
    ConsultationDTO consultationSave = service.save(consultation);
    Assertions.assertEquals(consultationSave.getScheduledTime()
      , Time.valueOf("15:30:00"));
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
