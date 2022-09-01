package br.com.dh.ClinicaOdontologica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.repository.ConsultationRepository;

@Service
public class ConsultationService
{
  @Autowired
  private ConsultationRepository consultationRepository;

  public Consultation save(Consultation consultation)
  {
    return consultationRepository.save(consultation);
  }
  public List<Consultation> findAll()
  {
    return consultationRepository.findAll();
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
