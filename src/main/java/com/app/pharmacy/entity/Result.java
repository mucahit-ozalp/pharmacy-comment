package com.app.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
