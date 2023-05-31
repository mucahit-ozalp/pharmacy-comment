package com.app.pharmacy.service;

import com.app.pharmacy.client.ICallCitiesOfGermany;
import com.app.pharmacy.client.IPharmacyClient;
import com.app.pharmacy.entity.CitiesModel;
import com.app.pharmacy.entity.Comment;
import com.app.pharmacy.entity.Pharmacy;
import com.app.pharmacy.entity.Result;
import com.app.pharmacy.exception.InvalidCityException;
import com.app.pharmacy.repository.ICommentRepository;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService implements IPharmacyService {

  private static final Logger logger = LoggerFactory.getLogger(
      PharmacyService.class);
  private IPharmacyClient iPharmacyClient;
  private ICallCitiesOfGermany iCallCitiesOfGermany;
  private ICommentRepository iCommentRepository;

  @Autowired
  public PharmacyService(@Qualifier("callOpenPharmacyWithOkHttp") IPharmacyClient iPharmacyClient,
      ICallCitiesOfGermany iCallCitiesOfGermany,
      ICommentRepository iCommentRepository) {
    this.iPharmacyClient = iPharmacyClient;
    this.iCallCitiesOfGermany = iCallCitiesOfGermany;
    this.iCommentRepository = iCommentRepository;
  }


  @Override
  public Pharmacy getOpenPharmacy(HttpSession session,String city) throws IOException, InvalidCityException {

    CitiesModel citiesModel =iCallCitiesOfGermany.getCitiesOfGermany();
    boolean containsSearchStr = citiesModel.getResult().getCities().stream().anyMatch(city::equalsIgnoreCase);
    session.setAttribute("citiesOfGermany",citiesModel.getResult().getCities().toString());

    logger.info((String) session.getAttribute("citiesOfGermany"));
    if (!containsSearchStr) {
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
//    pharmacy.getResult().stream()
//        .forEach(result -> result.setComment(
//            (Comment) comment.stream().filter(obj -> obj.getPharmacyName().equals(result.getName()))));

    return pharmacy;
  }
}
