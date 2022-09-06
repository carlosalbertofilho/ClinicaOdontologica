package br.com.dh.ClinicaOdontologica.util;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Dentist;

@Component
public class DentistUtil
{
  @Autowired
  private static final ModelMapper modelMap = new ModelMapper();

  public static DentistDTO convertToDTO(Dentist dentist)
  {
    return modelMap.map(dentist, DentistDTO.class);
  }
  public static Dentist convertToEntity(DentistDTO dentistDTO)
  {
    return modelMap.map(dentistDTO, Dentist.class);
  }
  public static Dentist dentistSave(DentistDTO dentistDTO)
  {
    Dentist dentist = convertToEntity(dentistDTO);
    dentist.setCreatedAt(LocalDate.now());
    return dentist;
  }
}
