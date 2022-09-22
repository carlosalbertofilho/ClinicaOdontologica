package br.com.dh.ClinicaOdontologica.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.service.DentistService;
import br.com.dh.ClinicaOdontologica.util.DentistUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/dentista")
public class DentistController
{
    @Autowired
    private DentistService dentistService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DentistDTO save(@Valid @RequestBody DentistDTO dentistDTO)
    {
      log.info("Creating Dentist: %s".formatted(dentistDTO.getLogin()));
      dentistDTO.setCreatedAt(LocalDate.now());
      dentistDTO.setPassword(bCryptPasswordEncoder
        .encode(dentistDTO.getPassword()));
      return dentistService.save(dentistDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DentistDTO> listDentists()
    {
      log.info("Find All Dentists");
      return dentistService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DentistDTO findDentistById(@PathVariable("id") Long id)
    {
      log.info("Find Dentist by ID: %d".formatted(id));
      return dentistService.findById(id)
        .map(DentistUtil::convertToDTO)
        .orElseThrow(() ->
          new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDentist(@PathVariable("id") Long id)
    {
      log.info("Delete Dentist by ID: %d".formatted(id));
      dentistService.findById(id)
        .map(dentist -> {
          dentistService.deleteById(id);
          return Void.TYPE;
        }).orElseThrow(() ->
          new ResponseStatusException(HttpStatus.NOT_FOUND
          , "Dentist not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDentist(@PathVariable("id") Long id,
                              @Valid @RequestBody DentistDTO dentistDTO)
    {
      log.info("Update Dentist by ID: %d".formatted(id));
      dentistDTO.setUpdateAt(LocalDate.now());
      dentistService.findById(id)
        .map(foundOnBase -> {
            modelMapper.map(dentistDTO, foundOnBase);
            dentistService.save(dentistDTO);
            return Void.TYPE;
        }).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND
            , "Dentist not found")
      );
    }
}
