package br.com.dh.ClinicaOdontologica.security;

import br.com.dh.ClinicaOdontologica.controller.AutenticationController;
import br.com.dh.ClinicaOdontologica.repository.UserRepository;
import br.com.dh.ClinicaOdontologica.service.ClientService;
import br.com.dh.ClinicaOdontologica.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.dh.ClinicaOdontologica.service.UserAuThService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserAuThService auThService;

  @Autowired
  private DentistService dentistService;
  @Autowired
  private ClientService clientService;
  @Autowired
  private TokenService tokenService;
  @Autowired
  private UserRepository userRepository;
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
    //provider.setUserDetailsService(dentistService);
    //provider.setUserDetailsService(clientService);
    return provider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/api/dentista").hasRole("ADMIN")
      .antMatchers("/api/cliente").hasRole("ADMIN")
      .antMatchers("/api/consulta").hasRole("ADMIN")
      .antMatchers("/h2/**").permitAll()
      //.antMatchers("/api/consulta/{id}").hasAnyAuthority("ADMIN")
      .anyRequest().authenticated()
      .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //adiciona para autenticar no front
      .and().addFilterBefore(new TokenAutotication(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);

    //.formLogin();
    // this will ignore only h2-console csrf, spring security 4+
    // http.csrf().ignoringAntMatchers("/h2/**");
    http.csrf().disable();
    //this will allow frames with same origin which is much more safe
    http.headers().frameOptions().sameOrigin();
  }
}
