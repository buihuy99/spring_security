package com.example.Spring.Security.service;

import com.example.Spring.Security.entity.Role;
import com.example.Spring.Security.entity.User;
import com.example.Spring.Security.repository.RoleRepository;
import com.example.Spring.Security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserserviceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public User saveUser(User user) {
    user.setPassword((passwordEncoder.encode(user.getPassword())));
    return userRepository.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public void addToUser(String username, String rolename) {
    User user = userRepository.findByEmail(username).get();
    Role role = roleRepository.findByName(rolename);
    user.getRoles().add(role);
  }
}
