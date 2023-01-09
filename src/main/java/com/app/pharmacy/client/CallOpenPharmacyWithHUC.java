package com.app.pharmacy.client;

import com.app.pharmacy.model.Pharmacy;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallOpenPharmacyWithHUC implements IPharmacyClient {

  private ObjectMapper objectMapper;
  private static final Logger logger = LoggerFactory.getLogger(
      CallOpenPharmacyWithHUC.class);
  private static String URL = "https://api.collectapi.com/germanyPharmacy/dutyPharmacy?city=";

  @Autowired
  public CallOpenPharmacyWithHUC(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Pharmacy getOpenPharmacy(String city) throws IOException {
    StringBuilder urlBuilder = new StringBuilder("https://api.collectapi.com/germanyPharmacy/dutyPharmacy?city=");
    urlBuilder.append(city);
    URL url = new URL(urlBuilder.toString());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("content-type", "application/json");
    con.setRequestProperty("authorization", "apikey 0biGE1SvmZu94tlU49N3RJ:75iZTcKEKc9VXXUulXqSFW");
    con.setRequestProperty("user-agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36");

    con.setRequestMethod("GET");
    con.connect();
    if (con.getResponseCode() != 200) {
      throw new IOException("Failed to get old release info: " + con.getResponseCode());
    }
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();

    Pharmacy pharmacy = objectMapper.readValue(content.toString(), Pharmacy.class);
    logger.info(pharmacy.toString());
    return pharmacy;
  }
}
