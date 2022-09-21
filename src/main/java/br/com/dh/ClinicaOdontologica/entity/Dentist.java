package br.com.dh.ClinicaOdontologica.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Dentist implements Serializable, UserDetails
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String login;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String registration;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate createdAt;

  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate updateAt;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
