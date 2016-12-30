package com.antonfenske.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LocationUser {

  @Id
  private Long id;

  private String name;

  @OneToOne(cascade = {CascadeType.ALL})
  private Location location;

  public LocationUser(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public LocationUser() { // jpa only
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
