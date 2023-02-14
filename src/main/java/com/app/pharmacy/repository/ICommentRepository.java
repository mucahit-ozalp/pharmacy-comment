package com.app.pharmacy.repository;

import com.app.pharmacy.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByIsActiveTrue(Sort sort);

  List<Comment> findByIsActiveFalse();
  @Query("SELECT c FROM Comment c WHERE c.isActive = true ")
  List<Comment> findAllComment(Sort sort);
  List<Comment> findByDescriptionIsNull();
  List<Comment> findByDescriptionIsNotNull();
  List<Comment> findByPersonNameStartingWith(String prefix);
  List<Comment> findByDescriptionLike(String likePattern);
  List<Comment> findByIdLessThan(Long age);
  List<Comment> findByPersonNameStartingWithOrderByPersonName(String name);
  Optional<Comment> findById(Long value);
  Comment  findByPersonName(String name);

  @Modifying
  @Transactional
  @Query("update Comment u set u.personLastName = :personLastName where u.personName = :personName")
  int updateCommentSetPersonLastNameForPersonName(@Param("personLastName") String personLastName,
      @Param("personName") String personName);
}
