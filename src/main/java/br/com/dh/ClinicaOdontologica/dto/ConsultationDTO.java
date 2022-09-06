package br.com.dh.ClinicaOdontologica.dto;

import java.sql.Time;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO
{
  private Long id;

  private Dentist dentist;

  private Client client;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate createdAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate updateAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate scheduledDate;

  @DateTimeFormat(pattern = "HH:mm:ss")
  private Time scheduledTime;
}
