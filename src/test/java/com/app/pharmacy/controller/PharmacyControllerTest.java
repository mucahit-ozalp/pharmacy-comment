package com.app.pharmacy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.app.pharmacy.entity.Pharmacy;
import com.app.pharmacy.entity.Result;
import com.app.pharmacy.exception.InvalidCityException;
import com.app.pharmacy.service.PharmacyService;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PharmacyControllerTest {

  @InjectMocks
  private PharmacyController pharmacyController;

  @Mock
  private PharmacyService pharmacyService;

  @Test
  void getOpenPharmacy_when_successful() throws IOException, InvalidCityException {
    when(pharmacyService.getOpenPharmacy(any())).thenReturn(getDummyPharmacyObj());
    Pharmacy pharmacy = pharmacyController.getOpenPharmacy(null);

    assertNotNull(pharmacy);
    assertEquals("aaa", pharmacy.getResult().get(0).getName());
  }


  private Pharmacy getDummyPharmacyObj() {
    Pharmacy pharmacy = new Pharmacy();
    pharmacy.setSuccess("true");
    Result result = new Result();
    result.setName("aaa");
    pharmacy.setResult(Arrays.asList(result));

    return pharmacy;

  }


}
