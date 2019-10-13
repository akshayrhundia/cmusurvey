package com.cmu.model;

import com.cmu.util.StringPrefixedSequenceIdGenerator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "QUESTION_AUDIO_TEXT")
public class QuestionAudioForText {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_que_id")
  @GenericGenerator(
      name = "sequence_que_id",
      strategy = "com.cmu.util.StringPrefixedSequenceIdGenerator",
      parameters = {
          @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
          @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUDIOTEXT_"),
          @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d") })
  private String id;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "title", nullable = false)
  private byte[] title;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "options", nullable = false)
  private List<String> options = new ArrayList<String>();

}
