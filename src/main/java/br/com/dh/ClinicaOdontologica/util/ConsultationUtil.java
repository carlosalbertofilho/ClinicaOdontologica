package br.com.dh.ClinicaOdontologica.util;

import org.springframework.stereotype.Component;

import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.entity.Dentist;

@Component
public class ConsultationUtil
{

  public static ConsultationDTO convertToDTO(Consultation consultation)
  {
    return ConsultationDTO.builder()
        .id(consultation.getId())
        .clientID(consultation.getClient().getId())
        .dentistID(consultation.getDentist().getId())
        .createdAt(consultation.getCreatedAt())
        .updateAt(consultation.getUpdateAt())
        .scheduledDate(consultation.getScheduledDate())
        .scheduledTime(consultation.getScheduledTime()).build();
  }
  public static Consultation convertoToEntity(
          ConsultationDTO consultationDTO
          , Client client
          , Dentist dentist )
  {
    return Consultation.builder()
      .id(consultationDTO.getId())
      .client(client)
      .dentist(dentist)
      .createdAt(consultationDTO.getCreatedAt())
      .updateAt(consultationDTO.getUpdateAt())
      .scheduledDate(consultationDTO.getScheduledDate())
      .scheduledTime(consultationDTO.getScheduledTime()).build();
  }
}
