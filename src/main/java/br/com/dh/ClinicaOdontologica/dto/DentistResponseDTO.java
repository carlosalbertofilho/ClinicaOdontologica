package br.com.dh.ClinicaOdontologica.dto;

import java.time.LocalDate;

import br.com.dh.ClinicaOdontologica.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DentistResponseDTO
{
  private Long id;

  private String name;

  private String lastName;

  private String login;

  private Role role;

  private LocalDate createdAt;

  private LocalDate updateAt;
}
