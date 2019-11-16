package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.cmu.model.QuestionAudioForAudio;


public interface QuestionAudioForAudioDao {

	QuestionAudioForAudio findById(String id);
	
	//Question findBySSO(String sso);
	
	void save(QuestionAudioForAudio Question);
	
	//void deleteBySSO(String sso);
	
	List<QuestionAudioForAudio> findAllQuestions();
	List<String> findAllQid();
	void deleteById(String id);

}

