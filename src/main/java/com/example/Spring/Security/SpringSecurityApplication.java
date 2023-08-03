package com.example.Spring.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class SpringSecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityApplication.class, args);
  }

  @Bean
  BCryptPasswordEncoder brBcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPPER_ADMIN"));
//
//			userService.saveUser(new User(null, "A full name", "a" , "a@gmail.com", "111111", new HashSet<>()));
//			userService.saveUser(new User(null, "B full name", "b" , "b@gmail.com", "222222", new HashSet<>()));
//			userService.saveUser(new User(null, "C full name", "c" , "c@gmail.com", "333333", new HashSet<>()));
//			userService.saveUser(new User(null, "D full name", "d" , "d@gmail.com", "444444", new HashSet<>()));
//
//			userService.addToUser("a@gmail.com","ROLE_USER");
//			userService.addToUser("b@gmail.com","ROLE_MANAGER");
//			userService.addToUser("c@gmail.com","ROLE_ADMIN");
//			userService.addToUser("d@gmail.com","ROLE_SUPPER_ADMIN");
//
//		};
//	}

}
