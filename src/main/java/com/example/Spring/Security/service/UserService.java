package com.example.Spring.Security.service;

import com.example.Spring.Security.entity.Role;
import com.example.Spring.Security.entity.User;

public interface UserService {
  User saveUser(User user);
  Role saveRole(Role role);
  void addToUser(String username, String rolename);
}
