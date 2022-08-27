package br.com.dh.ClinicaOdontologica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.repository.DentistRepository;

@Service
public class DentistService 
{
    @Autowired
    private DentistRepository dentistRepository;   
    
    public Dentist save (Dentist dentist)
    {
        return dentistRepository.save(dentist);
    }
    public List<Dentist> findAll()
    {
        return dentistRepository.findAll();
    }
    public Optional<Dentist> findById(Long id)
    {
        return dentistRepository.findById(id);
    }
    public void deleteById(Long id)
    {
        dentistRepository.deleteById(id);
    }
}
