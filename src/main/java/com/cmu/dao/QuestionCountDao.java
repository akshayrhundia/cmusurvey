package com.cmu.dao;

import java.util.List;

import com.cmu.model.Count;
import com.cmu.model.QuestionText;


public interface QuestionCountDao {

	
	void save(Count cnt);
	int getMax();
	
}

