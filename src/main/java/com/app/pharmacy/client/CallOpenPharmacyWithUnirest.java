package com.app.pharmacy.client;

import com.app.pharmacy.model.Pharmacy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallOpenPharmacyWithUnirest implements IPharmacyClient {

  private ObjectMapper objectMapper;
  private static final Logger logger = LoggerFactory.getLogger(
      CallOpenPharmacyWithHUC.class);

  @Autowired
  public CallOpenPharmacyWithUnirest(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Pharmacy getOpenPharmacy(String city) throws IOException {
    try {
      StringBuilder urlBuilder = new StringBuilder(
          "https://api.collectapi.com/germanyPharmacy/dutyPharmacy?city=");
      urlBuilder.append(city);
      HttpResponse<String> response = Unirest.get(urlBuilder.toString())
          .header("content-type", "application/json")
          .header("authorization", "apikey 0biGE1SvmZu94tlU49N3RJ:75iZTcKEKc9VXXUulXqSFW")
          .asString();
      if (response.getStatus() != 200) {
        throw new Exception("Failed to get old release info: " + response.getStatusText());
      }
      Pharmacy pharmacy = objectMapper.readValue(response.getBody(), Pharmacy.class);
      logger.info(pharmacy.toString());
      return pharmacy;
    } catch (UnirestException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
