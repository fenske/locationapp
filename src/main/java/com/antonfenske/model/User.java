package com.antonfenske.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

  @Id
  private Long id;

  private String name;

  @OneToOne(cascade = {CascadeType.ALL})
  private Location location;

  public User(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public User() { // jpa only
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
}
