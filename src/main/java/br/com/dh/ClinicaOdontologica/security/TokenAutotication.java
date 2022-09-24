package br.com.dh.ClinicaOdontologica.security;

import br.com.dh.ClinicaOdontologica.repository.UserRepository;
import br.com.dh.ClinicaOdontologica.service.UserAuThService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenAutotication extends OncePerRequestFilter {
  TokenService tokenService;
  UserAuThService userAuThService;

  public TokenAutotication(TokenService tokenService, UserAuThService userAuThService) {
    this.tokenService = tokenService;
    this.userAuThService = userAuThService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = recoverToken(request);

    boolean valido = tokenService.verifyToken(token);
    System.out.println(valido);

    if (valido){
      autenticateUser(token);
    }
    filterChain.doFilter(request, response);
  }

  private void autenticateUser(String token){
    try {
      String username = tokenService.getUserName(token);
      UserDetails user = userAuThService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    } catch (Exception e) {
        log.error(e.getMessage());
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
