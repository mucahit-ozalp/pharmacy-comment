package com.app.pharmacy.service;

import com.app.pharmacy.client.IPharmacyClient;
import com.app.pharmacy.dto.CommentDto;
import com.app.pharmacy.dto.ICommentDto;
import com.app.pharmacy.model.Comment;
import com.app.pharmacy.model.Pharmacy;
import com.app.pharmacy.model.Result;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService implements IPharmacyService{
  private IPharmacyClient iPharmacyClient;
  private CommentDto commentDto;

  @Autowired
  public PharmacyService(
      @Qualifier("callOpenPharmacyWithOkHttp") IPharmacyClient iPharmacyClient,CommentDto commentDto ) {
    this.iPharmacyClient = iPharmacyClient;
    this.commentDto=commentDto;

  }


  @Override
  public Pharmacy getOpenPharmacy(String city) throws IOException {
    Pharmacy pharmacy=iPharmacyClient.getOpenPharmacy(city);
    List<Comment> comment=commentDto.list();
    for (Comment commentObj:comment){
      for (Result result:pharmacy.getResult())
      if(commentObj.getPharmacyName().equals(result.getName())){
        result.setComment(commentObj);
      }
    }


    return pharmacy;
  }
}
