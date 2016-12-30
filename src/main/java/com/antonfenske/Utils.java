package com.antonfenske;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.util.Map;

public class Utils {

  public static String getPrincipalProperty(Principal principal, String property) {
    return ((Map<String, String>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails()).get(property);
  }
}
