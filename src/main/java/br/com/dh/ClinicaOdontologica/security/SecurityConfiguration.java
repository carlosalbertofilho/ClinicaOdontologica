package br.com.dh.ClinicaOdontologica.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/api/dentista").hasAnyAuthority("DENTISTA", "ADMIN")
      .antMatchers("/api/cliente").hasAnyAuthority("CLIENTE", "ADMIN")
      .antMatchers("/api/consulta").hasAnyAuthority("ADMIN")
      .antMatchers("/api/consulta/{id}").hasAnyAuthority("CLIENTE", "DENTISTA", "ADMIN")
      .anyRequest()
      .authenticated().and()
      .formLogin();
  }
}
