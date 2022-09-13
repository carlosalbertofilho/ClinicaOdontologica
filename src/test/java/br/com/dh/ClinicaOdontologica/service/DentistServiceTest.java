package br.com.dh.ClinicaOdontologica.service;

import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.service.DentistService;
import br.com.dh.ClinicaOdontologica.util.DentistUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class DentistServiceTest {

  @Autowired
  DentistService service;

  static Dentist dentist;

  @BeforeAll
  static void doBefore(){
    dentist = new Dentist();
    dentist.setRegistration("111111");
    dentist.setName("Claudio");
    dentist.setLastName("Duarte");
    dentist.setLogin("claudio.duarte");
    dentist.setPassword("cd1234");
    dentist.setIsAdmin(true);
  }

  @Test
  void successSave(){
    DentistDTO dentistSave = service.save(DentistUtil.convertToDTO(dentist));
    Assertions.assertNotNull(dentistSave.getId());

  }

  @Test
  void findAllDentist(){
    DentistDTO dentistSave = service.save(DentistUtil.convertToDTO(dentist));
    List<DentistDTO> resultList = service.findAll();
    Assertions.assertNotNull(resultList);

  }

  @Test
  void findADentist(){
    DentistDTO dentistSave = service.save(DentistUtil.convertToDTO(dentist));
    Optional<Dentist> resultList = service.findById(1L);
    Assertions.assertNotNull(resultList);

  }


}
