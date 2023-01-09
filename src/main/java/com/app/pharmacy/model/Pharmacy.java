package com.app.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Pharmacy  {
  @JsonProperty("success")
  private String success;
  @JsonProperty("result")
  private List<Result>  result;

}
