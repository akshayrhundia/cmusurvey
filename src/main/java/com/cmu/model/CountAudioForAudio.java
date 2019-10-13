package com.cmu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "QUESTION_COUNT_AUDIO_AUDIO")
public class CountAudioForAudio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

}
