package com.app.pharmacy.service;

import com.app.pharmacy.model.Pharmacy;
import com.app.pharmacy.model.Result;
import java.io.IOException;

public interface IPharmacyService {

  Pharmacy getOpenPharmacy(String city) throws IOException;

}
