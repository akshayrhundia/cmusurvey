package com.cmu.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.cmu.model.CountAudioForAudio;

@Repository("questionCountAudioForAudioDao")
public class QuestionCountAudioForAudioDaoImpl extends AbstractDao<Integer, CountAudioForAudio> implements QuestionCountAudioForAudioDao {

	public void save(CountAudioForAudio cnt) {
		persist(cnt);
	}

	@Override
	public int getMax() {
		Criteria criteria = getSession().createCriteria(CountAudioForAudio.class).setProjection(Projections.max("id"));
		Integer max = (Integer) criteria.uniqueResult();
		return max;
	}

}
