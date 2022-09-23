package br.com.dh.ClinicaOdontologica.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import br.com.dh.ClinicaOdontologica.entity.UserAdmin;
import br.com.dh.ClinicaOdontologica.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenAutotication extends OncePerRequestFilter {
  TokenService tokenService;
  UserRepository userRespository;

  public TokenAutotication(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRespository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = recoverToken(request);

    boolean valido = tokenService.verifyToken(token);
    System.out.println(valido);

    if (valido == true){
      autenticateUser(token);
    }
    filterChain.doFilter(request, response);
  }

  private void autenticateUser(String token){
    try {
      String username = tokenService.getUserName(token);
      UserAdmin user = userRespository.findByUsername(username).get();
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    } catch (Exception e) {
        log.error(e.getMessage());
        new ResponseStatusException(HttpStatus.NOT_FOUND
          , "Client not found");
    }
  }

  private String recoverToken(HttpServletRequest request) {
    String getToken = request.getHeader("Authorization");
    if (getToken == null || getToken.isEmpty() || !getToken.startsWith("Bearer ")){
      return null;
    }
    return getToken.substring(7,getToken.length());
  }
}
