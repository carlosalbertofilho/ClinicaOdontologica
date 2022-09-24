package br.com.dh.ClinicaOdontologica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.ClinicaOdontologica.entity.Client;
import br.com.dh.ClinicaOdontologica.entity.Consultation;
import br.com.dh.ClinicaOdontologica.entity.Dentist;

public interface ConsultationRepository extends JpaRepository<Consultation, Long>
{
    public List<Consultation> findByClient(Client client);
    public List<Consultation> findByDentist(Dentist dentist);
}
