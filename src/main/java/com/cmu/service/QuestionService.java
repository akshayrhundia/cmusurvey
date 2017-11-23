package com.cmu.service;

import java.util.List;

import com.cmu.model.QuestionAudioForAudio;
import com.cmu.model.QuestionAudioForText;
import com.cmu.model.QuestionText;

public interface QuestionService {

	QuestionText findTextById(int id);

	// Question findBySSO(String sso);

	void saveTextQuestion(QuestionText Question);

	void updateTextQuestionTitle(QuestionText Question);

	// void deleteQuestionBySSO(String sso);

	List<QuestionText> findAllTextQuestions();

	// boolean isQuestionSSOUnique(Integer id, String sso);

	QuestionAudioForText findAudioForTextById(String id);
	QuestionAudioForAudio findAudioForAudioById(String id);

	// Question findBySSO(String sso);

	void saveAudioQuestionForText(QuestionAudioForText Question);
	
	void saveAudioQuestionForAudio(QuestionAudioForAudio Question);

	//void updateAudioForAudioQuestion(QuestionAudioForAudio Question);
	//void updateAudioForTextQuestion(QuestionAudioForText Question);

	// void deleteQuestionBySSO(String sso);

	List<QuestionAudioForAudio> findAllAudioForAudioQuestions();
	List<QuestionAudioForText> findAllAudioForTextQuestions();

	void deleteTextById(int id);

	void deleteAudioForAudioById(String id);
	void deleteAudioForTextById(String id);
}