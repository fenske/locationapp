package com.antonfenske.controllers;

import static com.antonfenske.Utils.getPrincipalProperty;

import com.antonfenske.model.LocationUser;
import com.antonfenske.model.LocationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class UserController {

  @Autowired
  private LocationUserRepository userRepository;

  @RequestMapping("/user")
  public LocationUser user(Principal principal) {
    return updateOrCreateUser(principal);
  }

  private LocationUser updateOrCreateUser(Principal principal) {
    long id = Long.parseLong(getPrincipalProperty(principal, "id"));
    Optional<LocationUser> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      String username = getPrincipalProperty(principal, "name");
      LocationUser user = new LocationUser(id, username);
      userRepository.save(user);
      return user;
    } else {
      return userOptional.get();
    }
  }
}
