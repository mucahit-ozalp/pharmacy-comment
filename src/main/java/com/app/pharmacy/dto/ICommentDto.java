package com.app.pharmacy.dto;

import com.app.pharmacy.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDto  extends JpaRepository<Comment, Long> {
}
