package com.cmu.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.cmu.model.CountAudioForText;

@Repository("questionCountAudioForTextDao")
public class QuestionCountAudioForTextDaoImpl extends AbstractDao<Integer, CountAudioForText> implements QuestionCountAudioForTextDao {

	public void save(CountAudioForText cnt) {
		persist(cnt);
	}

	@Override
	public int getMax() {
		Criteria criteria = getSession().createCriteria(CountAudioForText.class).setProjection(Projections.max("id"));
		Integer max = (Integer) criteria.uniqueResult();
		return max;
	}

}
