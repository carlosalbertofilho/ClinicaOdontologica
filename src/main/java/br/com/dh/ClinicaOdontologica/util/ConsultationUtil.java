package br.com.dh.ClinicaOdontologica.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
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
}
