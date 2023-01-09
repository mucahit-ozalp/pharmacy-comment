package com.app.pharmacy.service;

import com.app.pharmacy.entity.Pharmacy;
import java.io.IOException;

public interface IPharmacyService {

  Pharmacy getOpenPharmacy(String city) throws IOException;

}
