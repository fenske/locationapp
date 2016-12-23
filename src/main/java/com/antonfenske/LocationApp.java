/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.antonfenske;

import com.antonfenske.model.Location;
import com.antonfenske.model.User;
import com.antonfenske.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class LocationApp extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserRepository userRepository;

  private User user; //FIXME this has to be removed in favor of proper user session management

  @RequestMapping("/user")
  public User user(Principal principal) {
    return updateOrCreateUser(principal);
  }

  @RequestMapping(path = "/savemarker", method = RequestMethod.POST)
  public ResponseEntity<?> saveMarker(@RequestBody Map<String, String> marker) {
    if (user != null) {
      user.setLocation(new Location(Double.parseDouble(marker.get("lat")), Double.parseDouble(marker.get("lng"))));
    }
    return ResponseEntity.ok(marker);
  }

  @RequestMapping("/marker")
  public Location marker() {
    if (user != null) {
      return user.getLocation();
    } else {
      return null;
    }
  }

  private User updateOrCreateUser(Principal principal) {
    long id = Long.parseLong(getPrincipalProperty(principal, "id"));
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      String username = getPrincipalProperty(principal, "name");
      User userEntity = new User(id, username);
      this.user = userEntity;
      userRepository.save(userEntity);
      return userEntity;
    } else {
      return userOptional.get();
    }
  }

  private String getPrincipalProperty(Principal principal, String property) {
    return ((Map<String, String>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails()).get(property);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
        .authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    // @formatter:on
  }

  public static void main(String[] args) {
    SpringApplication.run(LocationApp.class, args);
  }

}
