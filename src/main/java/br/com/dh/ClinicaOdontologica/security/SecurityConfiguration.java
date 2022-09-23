package br.com.dh.ClinicaOdontologica.security;

import br.com.dh.ClinicaOdontologica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
  private TokenService tokenService;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception{
    return super.authenticationManager();
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(auThService)
      .passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
      .antMatchers("/login").permitAll()
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

    //this will allow frames with same origin which is much more safe
//    http.headers().frameOptions().sameOrigin();
  }
}
