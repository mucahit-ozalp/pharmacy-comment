package com.app.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CitiesModel {
  @JsonProperty("success")
  private String success;
  @JsonProperty("result")
  private Cities result;

}
