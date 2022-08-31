package br.com.dh.ClinicaOdontologica.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.repository.DentistRepository;

@Service
public class DentistService
{
    @Autowired
    private DentistRepository dentistRepository;

    private static final Logger log = LogManager.getLogger(DentistRepository.class.getName());

    public Dentist save(Dentist dentist)
    {
        log.info("Creating Dentist: %s".formatted(dentist.getLogin()));
        dentist.setCreatedAt(LocalDate.now());
        return dentistRepository.save(dentist);
    }
    public List<Dentist> findAll()
    {
        log.info("Find All Dentists");
        return dentistRepository.findAll();
    }
    public Optional<Dentist> findById(Long id)
    {
        log.info("Find Dentist by ID: %d".formatted(id));
        return dentistRepository.findById(id);
    }
    public void deleteById(Long id)
    {
        log.info("Delete Dentist by ID: %d".formatted(id));
        dentistRepository.deleteById(id);
    }
}
