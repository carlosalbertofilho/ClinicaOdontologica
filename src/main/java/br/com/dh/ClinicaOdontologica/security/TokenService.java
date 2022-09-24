package br.com.dh.ClinicaOdontologica.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
  @Value("${clinicaOdontologica.jwt.expirations}")
  private String expiration;

  @Value("${clinicaOdontologica.jwt.secret}")
  private String secret;
  public String generateToken(Authentication authentication){
    UserDetails userLogged = (UserDetails) authentication.getPrincipal();

    Date dataHoje = new Date();
    Date dataExpiracao = new Date(dataHoje.getTime() + Long.parseLong(expiration));     //para definir a data de expiracao

    //applicatioProperties alterar

    return Jwts.builder()
      .setIssuer("Api Dh Ecommerce")
      .setSubject(userLogged.getUsername())
      .setIssuedAt(dataHoje)
      .setExpiration(dataExpiracao)
      .signWith(SignatureAlgorithm.HS256,secret)
      .compact();
  }

  public boolean verifyToken(String token) {
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  public  String getUserName(String token){
    Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }
}
