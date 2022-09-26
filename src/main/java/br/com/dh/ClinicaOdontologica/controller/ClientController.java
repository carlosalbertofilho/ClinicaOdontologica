package br.com.dh.ClinicaOdontologica.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.dh.ClinicaOdontologica.dto.ClientDTO;
import br.com.dh.ClinicaOdontologica.dto.ClientResponseDTO;
import br.com.dh.ClinicaOdontologica.service.ClientService;
import br.com.dh.ClinicaOdontologica.util.ClientUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/cliente")
public class ClientController
{
  @Autowired
  private ClientService clientService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClientResponseDTO save(@Valid @RequestBody ClientDTO clientDTO)
  {
    log.info("Creating Client: %s".formatted(clientDTO.getLogin()));
    clientDTO.setCreatedAt(LocalDate.now());
    clientDTO.setPassword(bCryptPasswordEncoder
      .encode(clientDTO.getPassword()));
    return clientService.save(clientDTO);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ClientResponseDTO> listClients()
  {
    log.info("Find All Clients");
    return clientService.FindAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ClientResponseDTO findClientById(@PathVariable("id") Long id)
  {
    log.info("Find Client by ID: %d".formatted(id));
    return clientService.findById(id)
      .map(ClientUtil::convertToResponse)
      .orElseThrow(() ->
          new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Client not found")
      );
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteClient(@PathVariable("id") Long id)
  {
    log.info("Delete Client by ID: %d".formatted(id));
    clientService.findById(id)
      .map(client -> {
        clientService.deleteById(id);
        return Void.TYPE;
      }).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND
          ,"Client not found"));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateClient(@PathVariable("id") Long id,
                            @Valid @RequestBody ClientDTO clientDTO)
  {
    log.info("Update Client by ID: %d".formatted(id));
    clientDTO.setUpdateAt(LocalDate.now());
    clientService.findById(id)
      .map(foundOnBase -> {
        modelMapper.map(clientDTO, foundOnBase);
        clientService.save(clientDTO);
        return Void.TYPE;
      }).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND
        , "Client not found"));
  }
}
