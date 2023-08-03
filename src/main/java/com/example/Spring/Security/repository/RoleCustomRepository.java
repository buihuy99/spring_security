package com.example.Spring.Security.repository;

import com.example.Spring.Security.entity.Role;
import com.example.Spring.Security.entity.User;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleCustomRepository extends CrudRepository<User, Long> {

  @Query("SELECT u.roles FROM User u WHERE u.email = :email")
  Collection<Role> getRoleByUser(@Param("email") String email);
}
