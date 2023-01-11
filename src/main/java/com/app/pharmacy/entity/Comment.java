package com.app.pharmacy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Table(name = "comment")
@Entity
@Data
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name="ID")
  private Long id;
  @Column(name="PHARMACYNAME")
  private String pharmacyName;
  @Column(name="PERSONNAME")
  private String personName;
  @Column(name="PERSONLASTNAME")
  private String personLastName;
  @Column(name="DESCRIPTION")
  private String description;
  @Column(name = "ISACTIVE")
  private Boolean isActive;
}
