package br.com.dh.ClinicaOdontologica.util;

import java.sql.Time;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.entity.Role;
import br.com.dh.ClinicaOdontologica.entity.UserAdmin;
import br.com.dh.ClinicaOdontologica.repository.ClientRepository;
import br.com.dh.ClinicaOdontologica.repository.ConsultationRepository;
import br.com.dh.ClinicaOdontologica.repository.DentistRepository;
import br.com.dh.ClinicaOdontologica.repository.UserRepository;

@Configuration
public class AddDataOnDB implements ApplicationRunner
{

  @Autowired private ClientRepository clientRepository;

  @Autowired private DentistRepository dentistRepository;

  @Autowired private ConsultationRepository consultationRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    userRepository.save(UserAdmin.builder()
                  .username("Teste")
                  .password(bCryptPasswordEncoder
                    .encode("123456"))
                  .role(Role.ROLE_ADMIN).build());

     clientRepository.save(Client.builder()
       .name("Eduardo")
       .lastName("Ananias")
       .login("eduardo.ananias@teste.com")
       .password(bCryptPasswordEncoder
         .encode("teste@12345"))
       .rg("32.123.123-12")
       .address("Rua 50")
       .role(Role.ROLE_CLIENT)
       .createdAt(LocalDate.of(2022, 9, 19))
       .updateAt(LocalDate.now()).build());

    dentistRepository.save(Dentist.builder()
      .name("Salomão")
      .lastName("Santos")
      .login("salomao.santos@teste.com")
      .password(bCryptPasswordEncoder
        .encode("teste@123456"))
      .registration("7654321")
      .role(Role.ROLE_DENTIST)
      .createdAt(LocalDate.of(2022, 3, 15))
      .updateAt(LocalDate.now()).build());

     consultationRepository.save(Consultation.builder()
       .client(Client.builder()
                 .name("Carlos")
                 .lastName("Filho")
                 .login("carlos.filho@teste.com")
                 .password(bCryptPasswordEncoder
                   .encode("teste@123456"))
                 .role(Role.ROLE_CLIENT)
                 .rg("12.123.123-12")
                 .address("Rua 10")
                 .createdAt(LocalDate.of(2022, 9, 22))
                 .updateAt(LocalDate.now()).build())
       .dentist(Dentist.builder()
                 .name("Denis")
                 .lastName("Carbone")
                 .login("denis.carbone@teste.com")
                 .password(bCryptPasswordEncoder
                  .encode("teste@123456"))
                 .registration("1234567")
                 .role(Role.ROLE_DENTIST)
                 .createdAt(LocalDate.now())
                 .updateAt(LocalDate.now()).build())
       .createdAt(LocalDate.now())
       .updateAt(LocalDate.now())
       .scheduledDate(LocalDate.of(2022, 9, 22))
       .scheduledTime(Time.valueOf("10:30:00")).build());

     consultationRepository.save(Consultation.builder()
       .client(Client.builder()
                 .name("Luna")
                 .lastName("Tchotchoza")
                 .login("luna@teste.com")
                 .password(bCryptPasswordEncoder
                 .encode("teste@123456"))
                 .role(Role.ROLE_CLIENT)
                 .rg("23.123.123-12")
                 .address("Rua 12")
                 .createdAt(LocalDate.now())
                 .updateAt(LocalDate.now()).build())
       .dentist(Dentist.builder()
                 .name("Lucas")
                 .lastName("Guimarães")
                 .login("lucas@teste.com")
                 .password(bCryptPasswordEncoder
                  .encode("teste@123456"))
                 .registration("25364795")
                 .role(Role.ROLE_DENTIST)
                 .createdAt(LocalDate.now())
                 .updateAt(LocalDate.now()).build())
       .createdAt(LocalDate.now())
       .updateAt(LocalDate.now())
       .scheduledDate(LocalDate.of(2022, 9, 22))
       .scheduledTime(Time.valueOf("15:30:00")).build());

  }

}
