package com.antonfenske.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {

  @Id
  @GeneratedValue
  private Long id;

  private Double lat;

  private Double lng;

  public Location(Double lat, Double lng) {
    this.lat = lat;
    this.lng = lng;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLng() {
    return lng;
  }
}
