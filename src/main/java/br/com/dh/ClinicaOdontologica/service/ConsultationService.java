package br.com.dh.ClinicaOdontologica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.repository.ConsultationRepository;
import br.com.dh.ClinicaOdontologica.util.ConsultationUtil;

@Service
public class ConsultationService
{
  @Autowired
  private ConsultationRepository consultationRepository;

  public ConsultationDTO save(ConsultationDTO consultationDTO)
  {
    Consultation consultation = consultationRepository
      .save(ConsultationUtil.convertoToEntity(consultationDTO));
    return ConsultationUtil.convertToDTO(consultation);
  }
  public List<ConsultationDTO> findAll()
  {
    return consultationRepository.findAll()
      .stream()
      .map(ConsultationUtil::convertToDTO)
      .collect(Collectors.toList());
  }
  public Optional<Consultation> findById(Long id)
  {
    return consultationRepository.findById(id);
  }
  public void deleteById(Long id)
  {
    consultationRepository.deleteById(id);
  }
}
