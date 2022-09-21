package br.com.dh.ClinicaOdontologica.security;

import br.com.dh.ClinicaOdontologica.service.UserAuThService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserAuThService auThService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(auThService)
      .passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/api/dentista").hasRole("ADMIN")
      .antMatchers("/api/cliente").hasRole("ADMIN")
      .antMatchers("/api/consulta").hasRole("ADMIN")
//      .antMatchers("/api/consulta/{id}").hasAnyAuthority("ADMIN")
      .anyRequest()
      .authenticated().and()
      .formLogin();
  }
}
