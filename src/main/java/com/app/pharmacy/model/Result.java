package com.app.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Result {
  @JsonProperty("name")
  private String name;
  @JsonProperty("dist")
  private String dist;
  @JsonProperty("address")
  private String address;
  @JsonProperty("phone")
  private String phone;
  @JsonProperty("loc")
  private String loc;
  private Comment comment;



}
