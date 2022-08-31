package br.com.dh.ClinicaOdontologica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.repository.ConsultationRepository;

@Service
public class ConsultationService
{
    @Autowired
    private ConsultationRepository consultationRepository;

    private static final Logger log = LogManager.getLogger(ConsultationRepository.class.getName());

    public Consultation save(Consultation consultation)
    {
        log.info("Creating Consultation");
        consultation.setCreatedAt(LocalDate.now());
        return consultationRepository.save(consultation);
    }
    public List<Consultation> findAll()
    {
        log.info("Find all Constations");
        return consultationRepository.findAll();
    }
    public Optional<Consultation> findById(Long id)
    {
        log.info("Find consultation by id: %d".formatted(id));
        return consultationRepository.findById(id);
    }
    public void deleteById(Long id)
    {
        log.info("Deleting consultation by id: %d".formatted(id));
        consultationRepository.deleteById(id);
    }
}
