package br.com.dh.ClinicaOdontologica;

import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.service.ConsultationService;
import br.com.dh.ClinicaOdontologica.util.ConsultationUtil;
import br.com.dh.ClinicaOdontologica.util.DentistUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ConsultationServiceTest {

  @Autowired
  ConsultationService service;

  static Consultation consultation;

  @BeforeAll
  void doBefore(){
    consultation = new Consultation();
    consultation.getClient();
    consultation.getDentist();
    consultation.getScheduledDate();
    consultation.getScheduledTime();
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
