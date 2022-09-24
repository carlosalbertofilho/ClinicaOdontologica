package br.com.dh.ClinicaOdontologica.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Consultation;

@Component
public class ConsultationUtil
{
  private static final ModelMapper MODEL_MAPPER = new ModelMapper();

  public static ConsultationDTO convertToDTO(Consultation consultation)
  {
    return MODEL_MAPPER.map(consultation, ConsultationDTO.class);
  }
  public static Consultation convertoToEntity(ConsultationDTO consultationDTO)
  {
    return MODEL_MAPPER.map(consultationDTO, Consultation.class);
  }

  public static ConsultationDTO convertToResponse(Consultation consultation)
  {
    return ConsultationDTO.builder()
      .client(ClientDTO.builder()
          .id(consultation.getClient().getId())
          .name(consultation.getClient().getName())
          .lastName(consultation.getClient().getLastName()).build())
      .dentist(DentistDTO.builder()
                .id(consultation.getDentist().getId())
                .name(consultation.getDentist().getName())
                .lastName(consultation.getDentist().getLastName()).build())
      .createdAt(consultation.getCreatedAt())
      .updateAt(consultation.getUpdateAt())
      .scheduledDate(consultation.getScheduledDate())
      .scheduledTime(consultation.getScheduledTime()).build();
  }
}
