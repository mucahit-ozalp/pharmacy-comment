package com.app.pharmacy.client;

import com.app.pharmacy.model.Pharmacy;
import java.io.IOException;

public interface IPharmacyClient {
  Pharmacy getOpenPharmacy(String city) throws IOException;

}
