package com.cmu.dao;

import java.util.List;

import com.cmu.model.CountAudioForAudio;
import com.cmu.model.QuestionText;


public interface QuestionCountAudioForAudioDao {

	
	void save(CountAudioForAudio cnt);
	int getMax();
	
}

