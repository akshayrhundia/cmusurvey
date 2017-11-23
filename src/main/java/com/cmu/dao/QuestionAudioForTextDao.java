package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.cmu.model.QuestionAudioForText;


public interface QuestionAudioForTextDao {

	QuestionAudioForText findById(String id);
	
	//Question findBySSO(String sso);
	
	void save(QuestionAudioForText Question);
	
	//void deleteBySSO(String sso);
	
	List<QuestionAudioForText> findAllQuestions();
	void deleteById(String id);

}

