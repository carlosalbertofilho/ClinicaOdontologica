package br.com.dh.ClinicaOdontologica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.dh.ClinicaOdontologica.dto.DentistDTO;
import br.com.dh.ClinicaOdontologica.dto.DentistResponseDTO;
import br.com.dh.ClinicaOdontologica.entity.Dentist;
import br.com.dh.ClinicaOdontologica.entity.Role;
import br.com.dh.ClinicaOdontologica.repository.DentistRepository;
import br.com.dh.ClinicaOdontologica.util.DentistUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DentistService implements UserDetailsService
{
    private DentistRepository dentistRepository;

    public DentistResponseDTO save(DentistDTO dentistDTO)
    {
      Dentist dentist = DentistUtil.convertToEntity(dentistDTO);
      dentist.setRole(Role.ROLE_DENTIST);
      return DentistUtil.convertToResponse(dentistRepository.save(dentist));
    }

    public List<DentistResponseDTO> findAll()
    {
      return dentistRepository.findAll()
        .stream()
        .map(DentistUtil::convertToResponse)
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
