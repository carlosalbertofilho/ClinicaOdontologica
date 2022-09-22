package br.com.dh.ClinicaOdontologica.security;

import br.com.dh.ClinicaOdontologica.entity.UserAdmin;
import br.com.dh.ClinicaOdontologica.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAutotication extends OncePerRequestFilter {
  TokenService tokenService;
  UserRepository usuarioRespository;

  public TokenAutotication(TokenService tokenService, UserRepository usuarioRespository) {
    this.tokenService = tokenService;
    this.usuarioRespository = usuarioRespository;
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
    String username = tokenService.getUserName(token);
    UserAdmin usuario = usuarioRespository.findByUsername(username);
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
  }

  private String recoverToken(HttpServletRequest request) {
    String getToken = request.getHeader("Authorization");
    if (getToken == null || getToken.isEmpty() || !getToken.startsWith("Bearer ")){
      return null;
    }
    return getToken.substring(7,getToken.length());
  }
}
