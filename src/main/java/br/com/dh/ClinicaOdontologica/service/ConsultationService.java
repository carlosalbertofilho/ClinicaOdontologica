package br.com.dh.ClinicaOdontologica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.repository.ClientRepository;
import br.com.dh.ClinicaOdontologica.repository.ConsultationRepository;
import br.com.dh.ClinicaOdontologica.repository.DentistRepository;
import br.com.dh.ClinicaOdontologica.util.ConsultationUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsultationService
{
  private ConsultationRepository consultationRepository;
  private ClientRepository clientRepository;
  private DentistRepository dentistRepository;

  public ConsultationDTO save(ConsultationDTO consultationDTO)
  {
    consultationDTO.setCreatedAt(LocalDate.now());
    Consultation consultation = consultationRepository
      .save(ConsultationUtil
        .convertoToEntity(consultationDTO
            , clientRepository.findById(consultationDTO.getClientID()).get()
            , dentistRepository.findById(consultationDTO.getDentistID()).get()));
    return ConsultationUtil.convertToDTO(consultation);
  }
  public List<ConsultationDTO> findAll()
  {
    return consultationRepository.findAll()
      .stream()
      .map(ConsultationUtil::convertToDTO)
      .collect(Collectors.toList());
  }

  public List<ConsultationDTO> findByClientId(Long id)
  {
    return consultationRepository.findByClient(clientRepository.findById(id).get())
          .stream()
          .map(ConsultationUtil::convertToDTO)
          .collect(Collectors.toList());

  }

  public List<ConsultationDTO> findByDentistId(Long id)
  {
    return consultationRepository.findByDentist(dentistRepository.findById(id).get())
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
