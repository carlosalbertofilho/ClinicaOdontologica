package br.com.dh.ClinicaOdontologica.dto;

import java.time.LocalDate;

import br.com.dh.ClinicaOdontologica.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {
  private Long id;

  private String name;

  private String lastName;

  private String login;

  private String address;

  private Role role;

  private LocalDate createdAt;

  private LocalDate updateAt;
}
