package br.com.dh.ClinicaOdontologica.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.service.ConsultationService;

@RestController
@RequestMapping("/consulta")
public class ConsultationController
{
    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consultation save(@Valid @RequestBody Consultation consultation)
    {
        return consultationService.save(consultation);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Consultation> listConsultations()
    {
        return consultationService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Consultation findConsultationById(@PathVariable Long id)
    {
        return consultationService.findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsultation(@PathVariable("id") Long id)
    {
        consultationService.findById(id)
            .map(consultation -> {
                consultationService.deleteById(id);
                return Void.TYPE;
            }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Consultation not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsultation(@PathVariable("id") Long id,
                                  @Valid @RequestBody Consultation consultation)
    {
        consultationService.findById(id)
            .map(foundOnBase -> {
                modelMapper.map(consultation, foundOnBase);
                consultationService.save(consultation);
                return Void.TYPE;
            }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Consultation not found")
            );
    }
}
