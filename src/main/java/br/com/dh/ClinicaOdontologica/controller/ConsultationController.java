package br.com.dh.ClinicaOdontologica.controller;

import java.time.LocalDate;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    log.info("Creating Consultation");
    consultation.setCreatedAt(LocalDate.now());
    return consultationService.save(consultation);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Consultation> listConsultations()
  {
    log.info("Find all Constations");
    return consultationService.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Consultation findConsultationById(@PathVariable Long id)
  {
    log.info("Find consultation by id: %d".formatted(id));
    return consultationService.findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND
          , "Consultation not found"));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteConsultation(@PathVariable("id") Long id)
  {
    log.info("Deleting consultation by id: %d".formatted(id));
    consultationService.findById(id)
      .map(consultation -> {
        consultationService.deleteById(id);
        return Void.TYPE;
      }).orElseThrow(() ->
          new ResponseStatusException(HttpStatus.NOT_FOUND
            , "Consultation not found"));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateConsultation(@PathVariable("id") Long id,
                                @Valid @RequestBody Consultation consultation)
  {
    log.info("Update consultation by id: %d".formatted(id));
    consultation.setUpdateAt(LocalDate.now());
    consultationService.findById(id)
      .map(foundOnBase -> {
        modelMapper.map(consultation, foundOnBase);
        consultationService.save(consultation);
        return Void.TYPE;
      }).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND
          , "Consultation not found")
      );
  }
}
