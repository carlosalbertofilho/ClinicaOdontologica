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

import br.com.dh.ClinicaOdontologica.dto.ConsultationDTO;
import br.com.dh.ClinicaOdontologica.service.ConsultationService;
import br.com.dh.ClinicaOdontologica.util.ConsultationUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/consulta")
public class ConsultationController
{
  @Autowired
  private ConsultationService consultationService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ConsultationDTO save(@Valid @RequestBody ConsultationDTO consultationDTO)
  {
    log.info("Creating Consultation");
    consultationDTO.setCreatedAt(LocalDate.now());
    return consultationService.save(consultationDTO);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ConsultationDTO> listConsultations()
  {
    log.info("Find all Constations");
    return consultationService.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ConsultationDTO findConsultationById(@PathVariable Long id)
  {
    log.info("Find consultation by id: %d".formatted(id));
    return consultationService.findById(id)
      .map(ConsultationUtil::convertToResponse)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND
          , "Consultation not found"));
  }

  @GetMapping("/buscarPorClient/{clientID}")
  @ResponseStatus(HttpStatus.OK)
  public List<ConsultationDTO> findConsultationByClient(@PathVariable Long clientID)
  {
    log.info("Find consultation by Client of the id: %d".formatted(clientID));
    return consultationService.findByClientId(clientID);
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
                                @Valid @RequestBody ConsultationDTO consultationDTO)
  {
    log.info("Update consultation by id: %d".formatted(id));
    consultationDTO.setUpdateAt(LocalDate.now());
    consultationService.findById(id)
      .map(foundOnBase -> {
        modelMapper.map(consultationDTO, foundOnBase);
        consultationService.save(consultationDTO);
        return Void.TYPE;
      }).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND
          , "Consultation not found")
      );
  }
}
