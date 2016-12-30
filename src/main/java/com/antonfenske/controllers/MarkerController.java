package com.antonfenske.controllers;

import static com.antonfenske.Utils.getPrincipalProperty;

import com.antonfenske.model.Location;
import com.antonfenske.model.User;
import com.antonfenske.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RestController
public class MarkerController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping("/marker")
  public Location marker(Principal principal) {
    Optional<User> userOptional = retrieveUserEntity(principal);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      return user.getLocation();
    } else {
      return null;
    }
  }

  @RequestMapping(path = "/savemarker", method = RequestMethod.POST)
  public ResponseEntity<?> saveMarker(Principal principal, @RequestBody Map<String, String> marker) {
    Optional<User> userOptional = retrieveUserEntity(principal);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      user.setLocation(new Location(Double.parseDouble(marker.get("lat")), Double.parseDouble(marker.get("lng"))));
      userRepository.save(user);
    }
    return ResponseEntity.ok(marker);
  }

  private Optional<User> retrieveUserEntity(Principal principal) {
    long userId = Long.parseLong(getPrincipalProperty(principal, "id"));
    return userRepository.findById(userId);
  }

}
