package com.cmu.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Table(name = "QUESTION_TEXT")
public class QuestionText {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "options", nullable = false)
  private List<String> options = new ArrayList<String>();

}
