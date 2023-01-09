package com.app.pharmacy.controller;

import com.app.pharmacy.entity.Pharmacy;
import com.app.pharmacy.service.IPharmacyService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
  private IPharmacyService iPharmacyService;

  @Autowired
  public PharmacyController(IPharmacyService iPharmacyService) {
    this.iPharmacyService = iPharmacyService;
  }

  @GetMapping("getopenpharmacy")
  public Pharmacy getOpenPharmacy(@RequestParam("city") String city) throws IOException {
    return iPharmacyService.getOpenPharmacy(city);
  }

}
