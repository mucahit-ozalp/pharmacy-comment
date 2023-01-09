package com.app.pharmacy.dto;

import com.app.pharmacy.model.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentDto   {
  @Autowired
  private   ICommentDto iCommentDto;

  public List<Comment> list() {
    return iCommentDto.findAll();
  }

}
