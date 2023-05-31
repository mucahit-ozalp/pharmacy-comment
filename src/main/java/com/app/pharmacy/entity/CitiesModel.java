package com.app.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesModel {
  @JsonProperty("success")
  private String success;
  @JsonProperty("result")
  private Cities result;

}
