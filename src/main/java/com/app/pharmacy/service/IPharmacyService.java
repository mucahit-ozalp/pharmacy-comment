package com.app.pharmacy.service;

import com.app.pharmacy.entity.Pharmacy;
import com.app.pharmacy.exception.InvalidCityException;
import java.io.IOException;
import javax.servlet.http.HttpSession;

public interface IPharmacyService {

  Pharmacy getOpenPharmacy(HttpSession session,String city) throws IOException, InvalidCityException;

}
