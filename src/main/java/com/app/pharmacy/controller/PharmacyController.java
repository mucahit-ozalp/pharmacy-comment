package com.app.pharmacy.controller;

import com.app.pharmacy.entity.Pharmacy;
import com.app.pharmacy.exception.InvalidCityException;
import com.app.pharmacy.service.IPharmacyService;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pharmacy")
@CrossOrigin()
public class PharmacyController {

  private static final Logger logger = LoggerFactory.getLogger(
      PharmacyController.class);
  private IPharmacyService iPharmacyService;

  @Autowired
  public PharmacyController(IPharmacyService iPharmacyService) {
    this.iPharmacyService = iPharmacyService;
  }

  @GetMapping("getopenpharmacy")
  public Pharmacy getOpenPharmacy(HttpSession session,@RequestParam("city") String city)
      throws IOException, InvalidCityException {
    try {
      return iPharmacyService.getOpenPharmacy(session,city);
    } catch (IOException e) {
      logger.error("process failed");
      throw new IOException("Process failed");
    } catch (InvalidCityException e) {
      logger.error("City name invalid");
      throw new InvalidCityException("City name invalid");
    }
  }


}
