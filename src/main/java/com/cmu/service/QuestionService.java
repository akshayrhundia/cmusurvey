package com.cmu.service;

import java.util.List;

import com.cmu.model.QuestionAudio;
import com.cmu.model.QuestionText;


public interface QuestionService {
	
	QuestionText findTextById(int id);
	
	//Question findBySSO(String sso);
	
	void saveTextQuestion(QuestionText Question);
	
	void updateTextQuestion(QuestionText Question);
	
	//void deleteQuestionBySSO(String sso);

	List<QuestionText> findAllTextQuestions(); 
	
	//boolean isQuestionSSOUnique(Integer id, String sso);

	QuestionAudio findAudioById(String id);
	
	//Question findBySSO(String sso);
	
	void saveAudioQuestion(QuestionAudio Question);
	
	void updateAudioQuestion(QuestionAudio Question);
	
	//void deleteQuestionBySSO(String sso);

	List<QuestionAudio> findAllAudioQuestions(); 
}