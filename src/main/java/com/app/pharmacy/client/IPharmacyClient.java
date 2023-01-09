package com.app.pharmacy.client;

import com.app.pharmacy.entity.Pharmacy;
import java.io.IOException;

public interface IPharmacyClient {
  Pharmacy getOpenPharmacy(String city) throws IOException;

}
