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

import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.service.ClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/cliente")
public class ClientController
{
  @Autowired
  private ClientService clientService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Client save(@Valid @RequestBody Client client)
  {
    log.info("Creating Client: %s".formatted(client.getLogin()));
    client.setCreatedAt(LocalDate.now());
    return clientService.save(client);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Client> listClients()
  {
    log.info("Find All Clients");
    return clientService.FindAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Client findClientById(@PathVariable("id") Long id)
  {
    log.info("Find Client by ID: %d".formatted(id));
    return clientService.findById(id)
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
        , "Client not found"));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateClient(@PathVariable("id") Long id,
                            @Valid @RequestBody Client client)
  {
    log.info("Update Client by ID: %d".formatted(id));
    clientService.findById(id)
      .map(foundOnBase -> {
        modelMapper.map(client, foundOnBase);
        clientService.deleteById(id);
        return Void.TYPE;
      }).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND
        , "Client not found"));
  }
}
