package br.com.dh.ClinicaOdontologica.security;

import br.com.dh.ClinicaOdontologica.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.dh.ClinicaOdontologica.service.UserAuThService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserAuThService auThService;

  @Autowired
  private DentistService dentistService;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(auThService);
    provider.setUserDetailsService(dentistService);
    return provider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/api/dentista").hasRole("ADMIN")
      .antMatchers("/api/cliente").hasRole("ADMIN")
      .antMatchers("/api/consulta").hasRole("ADMIN")
      .antMatchers("/h2/**").permitAll()
//      .antMatchers("/api/consulta/{id}").hasAnyAuthority("ADMIN")
      .anyRequest()
      .authenticated().and()
      .formLogin();
    // this will ignore only h2-console csrf, spring security 4+
    // http.csrf().ignoringAntMatchers("/h2/**");
    http.csrf().disable();
    //this will allow frames with same origin which is much more safe
    http.headers().frameOptions().sameOrigin();
  }
}
