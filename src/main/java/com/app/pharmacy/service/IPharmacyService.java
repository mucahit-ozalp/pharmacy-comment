package com.app.pharmacy.service;

import com.app.pharmacy.entity.Pharmacy;
import com.app.pharmacy.exception.InvalidCityException;
import java.io.IOException;

public interface IPharmacyService {

  Pharmacy getOpenPharmacy(String city) throws IOException, InvalidCityException;

}
