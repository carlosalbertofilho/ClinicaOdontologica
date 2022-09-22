package br.com.dh.ClinicaOdontologica.security;

import br.com.dh.ClinicaOdontologica.entity.UserAdmin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
  @Value("${clinicaOdontologica.jwt.expirations}")
  private String expiration;

  @Value("${clinicaOdontologica.jwt.secret}")
  private String secret;
  public String generateToken(Authentication authentication){
    UserAdmin userLogged = (UserAdmin) authentication.getPrincipal();   //fazer cast

    Date dataHoje = new Date();
    Date dataExpiracao = new Date(dataHoje.getTime() + Long.parseLong(expiration));     //para definir a data de expiracao
    String token =  Jwts.builder()
      .setIssuer("Api Dh Ecommerce")
      .setSubject(userLogged.getUsername())
      .setIssuedAt(dataHoje)
      .setExpiration(dataExpiracao)
      .signWith(SignatureAlgorithm.HS256,secret)
      .compact();

    //applicatioProperties alterar

    return token;
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
    String username = claims.getSubject();
    return username;
  }
}
