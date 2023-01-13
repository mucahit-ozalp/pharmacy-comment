package com.app.pharmacy.client;

import com.app.pharmacy.entity.Pharmacy;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallOpenPharmacyWithOkHttp implements IPharmacyClient {

  private static String URL = "https://api.collectapi.com/germanyPharmacy/dutyPharmacy";
  private static final Logger logger = LoggerFactory.getLogger(
      CallOpenPharmacyWithOkHttp.class);

  private ObjectMapper objectMapper;


  @Autowired
  public CallOpenPharmacyWithOkHttp( ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }


  @Override
  public Pharmacy getOpenPharmacy(String city) throws IOException {
    OkHttpClient okHttpClient=new OkHttpClient();
    HttpUrl.Builder urlBuilder
        = HttpUrl.parse(URL).newBuilder();
    urlBuilder.addQueryParameter("city", city);

    String url = urlBuilder.build().toString();

    Request request = new Request.Builder()
        .url(url)
        .addHeader("content-type", "application/json")
        .addHeader("authorization", "apikey 0biGE1SvmZu94tlU49N3RJ:75iZTcKEKc9VXXUulXqSFW")
        .build();
    Call call = okHttpClient.newCall(request);
    Response response = call.execute();
    if (response.code() != 200) {
      throw new IOException("Failed to get old release info: " + response.code());
    }
    logger.info(response.toString());
    Pharmacy pharmacy = objectMapper.readValue(response.body().string(), Pharmacy.class);
    return pharmacy;
  }
}
