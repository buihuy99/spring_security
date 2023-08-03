package com.example.Spring.Security.service;

import com.example.Spring.Security.auth.AuthenticationRequest;
import com.example.Spring.Security.auth.AuthenticationResponse;
import com.example.Spring.Security.entity.Role;
import com.example.Spring.Security.entity.User;
import com.example.Spring.Security.repository.RoleCustomRepository;
import com.example.Spring.Security.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final RoleCustomRepository roleCustomRepository;
  private final JwtService jwtService;

  public AuthenticationResponse authenticate(
      AuthenticationRequest authenticationRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
            authenticationRequest.getPassword()));
    final User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
    final Collection<Role> role = roleCustomRepository.getRoleByUser(user.getEmail());

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    Set<Role> set = new HashSet<>();
    role.forEach(c -> set.add(new Role(c.getName())));
    user.setRoles(set);
    set.forEach(i -> authorities.add(new SimpleGrantedAuthority(i.getName())));
    var jwtToken = jwtService.genarateToken(user, authorities);
    var jwtRefreshToken = jwtService.genarateRefreshToken(user, authorities);
    return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
  }
}

