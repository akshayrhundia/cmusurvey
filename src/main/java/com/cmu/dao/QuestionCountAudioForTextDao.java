package com.cmu.dao;

import java.util.List;

import com.cmu.model.CountAudioForText;
import com.cmu.model.QuestionText;


public interface QuestionCountAudioForTextDao {

	
	void save(CountAudioForText cnt);
	int getMax();
	
}

