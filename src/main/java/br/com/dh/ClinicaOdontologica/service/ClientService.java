package br.com.dh.ClinicaOdontologica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.repository.ClientRepository;

@Service
public class ClientService 
{
    @Autowired
    private ClientRepository clientRepository;

    private static final Logger log = LogManager.getLogger(ClientRepository.class.getName());

    public Client save(Client client)
    {
        log.info("Creating Client: %s".formatted(client.getLogin()));
        client.setCreatedAt(LocalDate.now());
        return clientRepository.save(client);
    }
    public List<Client> FindAll()
    {
        log.info("Find All Clients");
        return clientRepository.findAll();
    }
    public Optional<Client> findById(Long id)
    {
        log.info("Find Client by ID: %d".formatted(id));
        return clientRepository.findById(id);
    }
    public void deleteById(Long id)
    {
        log.info("Delete Client by ID: %d".formatted(id));
        clientRepository.deleteById(id);
    }
}
