package com.app.pharmacy.controller;

import com.app.pharmacy.client.CallOpenPharmacyWithHUC;
import com.app.pharmacy.entity.Pharmacy;
import com.app.pharmacy.exception.InvalidCityException;
import com.app.pharmacy.service.IPharmacyService;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

  private static final Logger logger = LoggerFactory.getLogger(
      PharmacyController.class);
  private IPharmacyService iPharmacyService;

  @Autowired
  public PharmacyController(IPharmacyService iPharmacyService) {
    this.iPharmacyService = iPharmacyService;
  }

  @GetMapping("getopenpharmacy")
  public Pharmacy getOpenPharmacy(@RequestParam("city") String city) throws IOException {
    try {
      return iPharmacyService.getOpenPharmacy(city);
    } catch (InvalidCityException e) {
      logger.error("process failed");
      //TODO will be add logger
//      throw new RuntimeException("process failed : " +e);
    }
    return null;
  }

}
