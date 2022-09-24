package br.com.dh.ClinicaOdontologica.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.dh.ClinicaOdontologica.entity.Role;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DentistDTO
{

  private Long id;

  @NotBlank(message = "Dentist name is mandatory to fill!")
  @Size(min = 2, max = 50, message = "Dentist Name accepts only upto 50 character and minimun 2 character")
  private String name;

  @NotBlank(message = "Dentist last name is mandatory to fill")
  @Size(min = 2, max = 50, message = "Dentist lastName accepts only upto 50 character and minimun 2 character")
  private String lastName;

  @Email(message = "Email invalid!")
  private String login;

  @Size(min = 6, max = 12, message = "Client password accepts only upto 12 character and minimun 6 character")
  private String password;

  @NotBlank(message = "Registration is mandatory to fill")
  private String registration;

  private Role role;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate createdAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate updateAt;
}
