package br.com.dh.ClinicaOdontologica.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Dentist;

@Component
public class DentistUtil
{

  private static final ModelMapper MODEL_MAPPER = new ModelMapper();

  public static DentistDTO convertToDTO(Dentist dentist)
  {
    return MODEL_MAPPER.map(dentist, DentistDTO.class);
  }
  public static Dentist convertToEntity(DentistDTO dentistDTO)
  {
    return MODEL_MAPPER.map(dentistDTO, Dentist.class);
  }
}
