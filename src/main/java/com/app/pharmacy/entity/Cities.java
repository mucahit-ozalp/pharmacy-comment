package com.app.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Cities {
  @JsonProperty("cities")
  private List<String> cities;

}
