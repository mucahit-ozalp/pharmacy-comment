package com.app.pharmacy.service;

import com.app.pharmacy.client.IPharmacyClient;
import com.app.pharmacy.exception.InvalidCityException;
import com.app.pharmacy.repository.ICommentRepository;
import com.app.pharmacy.entity.Comment;
import com.app.pharmacy.entity.Pharmacy;
import com.app.pharmacy.entity.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService implements IPharmacyService {

  private IPharmacyClient iPharmacyClient;
  private ICommentRepository iCommentRepository;

  private final List<String> cityList= Arrays.asList("hamburg");
  @Autowired
  public PharmacyService(
      @Qualifier("callOpenPharmacyWithHUC") IPharmacyClient iPharmacyClient,
      ICommentRepository iCommentRepository) {
    this.iPharmacyClient = iPharmacyClient;
    this.iCommentRepository = iCommentRepository;
  }


  @Override
  public Pharmacy getOpenPharmacy(String city) throws IOException, InvalidCityException {

      if(!cityList.contains(city)){
        throw new InvalidCityException("City name invalid");
      }

    Pharmacy pharmacy = iPharmacyClient.getOpenPharmacy(city);
    List<Comment> comment = iCommentRepository.findByIsActiveTrue(Sort.by("personName"));
    for (Comment commentObj : comment) {
      for (Result result : pharmacy.getResult()) {
        if (commentObj.getPharmacyName().equals(result.getName())) {
          result.setComment(commentObj);
        }
      }
    }

    return pharmacy;
  }
}
