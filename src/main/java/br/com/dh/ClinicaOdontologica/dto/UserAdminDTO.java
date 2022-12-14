package br.com.dh.ClinicaOdontologica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAdminDTO {

  @NotEmpty
  @Size(min = 6)
  private String username;
  @NotEmpty
  @Size(min = 6)
  private String password;
  public UsernamePasswordAuthenticationToken convertUser(){
    return new UsernamePasswordAuthenticationToken(this.username, this.password);
  }

}
