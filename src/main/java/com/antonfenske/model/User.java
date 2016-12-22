package com.antonfenske.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  private Long id;

  private String name;

  public User(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public User() { // jpa only
  }
}
