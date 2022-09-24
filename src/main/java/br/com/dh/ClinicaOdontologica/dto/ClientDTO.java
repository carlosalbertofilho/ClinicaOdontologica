package br.com.dh.ClinicaOdontologica.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.dh.ClinicaOdontologica.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO
{
  private Long id;

  @NotBlank(message = "Client name is mandatory to fill!")
  @Size(min = 2, max = 50, message = "Client Name accepts only upto 50 character and minimun 2 character")
  private String name;

  @NotBlank(message = "Client last name is mandatory to fill")
  @Size(min = 2, max = 50, message = "Client lastName accepts only upto 50 character and minimun 2 character")
  private String lastName;

  @NotBlank(message = "Login is mandatory to fill")
  @Email(message = "Email invalid!")
  private String login;

  @NotBlank(message = "Password is mandatory to fill")
  @Size(min = 6, max = 12, message = "Client password accepts only upto 12 character and minimun 6 character")
  private String password;

  @NotBlank(message = "RG is mandatory to fill")
  @Pattern(regexp = "(^\\d{1,2}).?(\\d{3}).?(\\d{3})-?(\\d{1}|X|x$)")
  private String rg;

  @NotBlank(message = "Address is mandatory to fill")
  private String address;

  private Role role;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate createdAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate updateAt;
}
