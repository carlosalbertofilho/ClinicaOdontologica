package br.com.dh.ClinicaOdontologica.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dh.ClinicaOdontologica.dto.TokenDTO;
import br.com.dh.ClinicaOdontologica.dto.UserAdminDTO;
import br.com.dh.ClinicaOdontologica.security.TokenService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
@AllArgsConstructor
public class AutenticationController {
  private TokenService tokenService;
  private AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity autentincar(@RequestBody @Valid UserAdminDTO userAdminDTO){
    try {
      UsernamePasswordAuthenticationToken loginUser = userAdminDTO.convertUser();
      Authentication authentication = authenticationManager.authenticate(loginUser);
      String token = tokenService.generateToken(authentication);
      return  new ResponseEntity(new TokenDTO(token, "Bearer "), HttpStatus.OK);
    }catch (AuthenticationException e){
      return  new ResponseEntity("Erro ao autenticar", HttpStatus.BAD_REQUEST);
    }

  }
}
