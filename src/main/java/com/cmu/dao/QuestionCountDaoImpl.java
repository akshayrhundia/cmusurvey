package com.cmu.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.cmu.model.Count;

@Repository("questionCountDao")
public class QuestionCountDaoImpl extends AbstractDao<Integer, Count> implements QuestionCountDao {

	public void save(Count cnt) {
		persist(cnt);
	}

	@Override
	public int getMax() {
		Criteria criteria = getSession().createCriteria(Count.class).setProjection(Projections.max("id"));
		Integer max = (Integer) criteria.uniqueResult();
		return max;
	}

}
