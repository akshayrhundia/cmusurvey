package com.cmu.dao;

import java.util.List;

import com.cmu.model.QuestionAudio;


public interface QuestionAudioDao {

	QuestionAudio findById(String id);
	
	//Question findBySSO(String sso);
	
	void save(QuestionAudio Question);
	
	//void deleteBySSO(String sso);
	
	List<QuestionAudio> findAllQuestions();

}

