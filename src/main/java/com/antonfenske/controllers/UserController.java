package com.antonfenske.controllers;

import static com.antonfenske.Utils.getPrincipalProperty;

import com.antonfenske.model.User;
import com.antonfenske.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping("/user")
  public User user(Principal principal) {
    return updateOrCreateUser(principal);
  }

  private User updateOrCreateUser(Principal principal) {
    long id = Long.parseLong(getPrincipalProperty(principal, "id"));
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      String username = getPrincipalProperty(principal, "name");
      User user = new User(id, username);
      userRepository.save(user);
      return user;
    } else {
      return userOptional.get();
    }
  }
}
