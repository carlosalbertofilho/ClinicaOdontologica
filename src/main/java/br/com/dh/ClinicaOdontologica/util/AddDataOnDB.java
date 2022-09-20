package br.com.dh.ClinicaOdontologica.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.repository.ClientRepository;
import br.com.dh.ClinicaOdontologica.repository.DentistRepository;

@Configuration
public class AddDataOnDB implements ApplicationRunner
{

  @Autowired private ClientRepository clientRepository;

  @Autowired private DentistRepository dentistRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    Client client = new Client();
    client.setName("Carlos");
    client.setLastName("Filho");
    client.setLogin("carlos.filho@teste.com");
    client.setPassword("teste@12345");
    client.setRg("12.123.123-12");
    client.setAddress("Rua 01");
    client.setCreatedAt(LocalDate.now());
    client.setUpdateAt(LocalDate.now());
    clientRepository.save(client);

    Client client2 = new Client();
    client2.setName("Luna");
    client2.setLastName("Tchotchoza");
    client2.setLogin("luna@teste.com");
    client2.setPassword("teste@12345");
    client2.setRg("22.123.123-12");
    client2.setAddress("Rua 10");
    client2.setCreatedAt(LocalDate.now());
    client2.setUpdateAt(LocalDate.now());
    clientRepository.save(client2);


    Dentist dentist = new Dentist();
    dentist.setName("Denis");
    dentist.setLastName("Carbone");
    dentist.setLogin("denis.carbone@teste.com");
    dentist.setPassword("teste@12345");
    dentist.setRegistration("123456");
    dentist.setIsAdmin(true);
    dentist.setCreatedAt(LocalDate.now());
    dentist.setUpdateAt(LocalDate.now());
    dentistRepository.save(dentist);

    Dentist dentist2 = new Dentist();
    dentist2.setName("Denis");
    dentist2.setLastName("Carbone");
    dentist2.setLogin("denis.carbone@teste.com");
    dentist2.setPassword("teste@12345");
    dentist2.setRegistration("123456");
    dentist2.setIsAdmin(false);
    dentist2.setCreatedAt(LocalDate.now());
    dentist2.setUpdateAt(LocalDate.now());
    dentistRepository.save(dentist2);


  }

}
