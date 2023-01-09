package com.app.pharmacy.repository;

import com.app.pharmacy.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
//will be added custom query
}
