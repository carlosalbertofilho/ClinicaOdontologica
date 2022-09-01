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
public class Dentist implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Dentist name is mandatory to fill!")
  @Size(min = 2, max = 50, message = "Dentist Name accepts only upto 50 character and minimun 2 character")
  @Column(nullable = false)
  private String name;

  @NotBlank(message = "Dentist last name is mandatory to fill")
  @Size(min = 2, max = 50, message = "Dentist lastName accepts only upto 50 character and minimun 2 character")
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(nullable = false)
  @Email(message = "Email invalid!")
  private String login;

  @Column(nullable = false)
  @Size(min = 6, max = 12, message = "Client password accepts only upto 12 character and minimun 6 character")
  private String password;

  @Column(nullable = false)
  private String registration;

  @Column(nullable = false)
  private Boolean isAdmin;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate createdAt;
}
