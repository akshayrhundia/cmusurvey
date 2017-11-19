package com.cmu.model;

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

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "QUESTION_AUDIO")
public class QuestionAudio {

	@Id
	@GenericGenerator(name = "sequence_que_id", strategy = "com.cmu.util.CustomIdGenerator")
	@GeneratedValue(generator = "sequence_que_id") 
	private String id;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "title", nullable = false)
	private byte[] title;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "options", nullable = false)
	private List<String> options = new ArrayList<String>();

}
