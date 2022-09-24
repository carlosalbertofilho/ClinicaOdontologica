package br.com.dh.ClinicaOdontologica.dto;

import java.sql.Time;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO
{
  private Long id;

  private DentistDTO dentist;

  private ClientDTO client;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate createdAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate updateAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate scheduledDate;

  @DateTimeFormat(pattern = "HH:mm:ss")
  private Time scheduledTime;
}
