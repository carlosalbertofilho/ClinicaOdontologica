package br.com.dh.ClinicaOdontologica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.entity.Role;
import br.com.dh.ClinicaOdontologica.repository.DentistRepository;
import br.com.dh.ClinicaOdontologica.util.DentistUtil;

@Service
public class DentistService implements UserDetailsService
{
    @Autowired
    private DentistRepository dentistRepository;

    public DentistDTO save(DentistDTO dentistDTO)
    {
      Dentist dentist = dentistRepository
        .save(DentistUtil.convertToEntity(dentistDTO));
      dentist.setRole(Role.ROLE_DENTIST);
      return DentistUtil.convertToDTO(dentist);
    }
    public List<DentistDTO> findAll()
    {
      return dentistRepository.findAll()
        .stream()
        .map(DentistUtil::convertToDTO)
        .collect(Collectors.toList());
    }
    public Optional<Dentist> findById(Long id)
    {
      return dentistRepository.findById(id);
    }
    public void deleteById(Long id)
    {
      dentistRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return dentistRepository.findByLogin(username)
        .orElseThrow(()
          -> new ResponseStatusException(HttpStatus.NOT_FOUND,
              "User Not Found"));
    }
}
