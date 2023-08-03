package com.example.Spring.Security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Spring.Security.entity.User;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String Secret_key = "123";

  public String genarateToken(User user, Collection<SimpleGrantedAuthority> authorities) {
    Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());
    return JWT.create()
        .withSubject(user.getEmail())
        .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
        .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(
            Collectors.toList()))
        .sign(algorithm);
  }

  public String genarateRefreshToken(User user, Collection<SimpleGrantedAuthority> authorities) {
    Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());
    return JWT.create()
        .withSubject(user.getEmail())
        .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
        .sign(algorithm);
  }
}
