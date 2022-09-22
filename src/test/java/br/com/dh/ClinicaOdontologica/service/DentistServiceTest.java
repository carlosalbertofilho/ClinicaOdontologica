package br.com.dh.ClinicaOdontologica.service;

import java.util.List;
import java.util.Optional;

import br.com.dh.ClinicaOdontologica.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.util.DentistUtil;


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
    dentist.setRole(Role.ROLE_DENTIST);
//    dentist.setIsAdmin(true);
  }

  @Test
  void successSave(){
    DentistDTO dentistSave = service.save(DentistUtil.convertToDTO(dentist));
    Assertions.assertNotNull(dentistSave.getId());

  }

  @Test
  void findAllDentist(){
    List<DentistDTO> resultList = service.findAll();
    Assertions.assertNotNull(resultList);

  }

  @Test
  void findADentist(){
    Optional<Dentist> resultList = service.findById(1L);
    Assertions.assertNotNull(resultList);

  }


}
