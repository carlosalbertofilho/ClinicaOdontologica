package br.com.dh.ClinicaOdontologica.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Client implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Client name is mandatory to fill!")
  @Size(min = 2, max = 50, message = "Client Name accepts only upto 50 character and minimun 2 character")
  @Column(nullable = false)
  private String name;

  @NotBlank(message = "Client last name is mandatory to fill")
  @Size(min = 2, max = 50, message = "Client lastName accepts only upto 50 character and minimun 2 character")
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @NotBlank(message = "Login is mandatory to fill")
  @Email(message = "Email invalid!")
  @Column(nullable = false)
  private String login;

  @NotBlank(message = "Password is mandatory to fill")
  @Size(min = 6, max = 12, message = "Client password accepts only upto 12 character and minimun 6 character")
  @Column(nullable = false)
  private String password;

  @NotBlank(message = "RG is mandatory to fill")
  @Pattern(regexp = "(^\\d{1,2}).?(\\d{3}).?(\\d{3})-?(\\d{1}|X|x$)")
  @Column(nullable = false)
  private String rg;

  @NotBlank(message = "Address is mandatory to fill")
  @Column(nullable = false)
  private String address;


  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate createdAt;
}
