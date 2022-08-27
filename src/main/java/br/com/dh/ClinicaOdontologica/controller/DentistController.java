package br.com.dh.ClinicaOdontologica.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.service.DentistService;

@RestController
@RequestMapping("/dentista")
public class DentistController 
{
    @Autowired
    private DentistService dentistService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dentist save(@RequestBody Dentist dentist)
    {
        return dentistService.save(dentist);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Dentist> listDentist()
    {
        return dentistService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dentist findDentistById(@PathVariable("id") Long id)
    {
        return dentistService.findById(id)
                .orElseThrow(() ->  
                    new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDentist(@PathVariable("id") Long id)
    {
        dentistService.findById(id)
            .map(dentist -> {
                dentistService.deleteById(id);
                return Void.TYPE;
            }).orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentist not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDentist(@PathVariable("id") Long id,
                              @RequestBody Dentist dentist)
    {
        dentistService.findById(id)
            .map(foundOnBase -> {
                modelMapper.map(dentist, foundOnBase);
                dentistService.save(dentist);
                return Void.TYPE;
            }).orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentist not found")
        );
    }
}
