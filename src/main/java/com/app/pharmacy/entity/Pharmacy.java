package com.app.pharmacy.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy  {
  @JsonProperty("success")
  private String success;
  @JsonProperty("result")
  private List<Result>  result;

}
