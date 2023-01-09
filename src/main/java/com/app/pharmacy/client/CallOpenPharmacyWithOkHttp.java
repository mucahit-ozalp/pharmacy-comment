package com.app.pharmacy.client;

import com.app.pharmacy.model.Pharmacy;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallOpenPharmacyWithOkHttp implements IPharmacyClient {

  private static String URL = "https://api.collectapi.com/germanyPharmacy/dutyPharmacy";
  private OkHttpClient okHttpClient;
  private ObjectMapper objectMapper;

  @Autowired
  public CallOpenPharmacyWithOkHttp(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
    this.okHttpClient = okHttpClient;
    this.objectMapper = objectMapper;
  }


  @Override
  public Pharmacy getOpenPharmacy(String city) throws IOException {

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
    Pharmacy pharmacy = objectMapper.readValue(response.body().string(), Pharmacy.class);
    return pharmacy;
  }
}
