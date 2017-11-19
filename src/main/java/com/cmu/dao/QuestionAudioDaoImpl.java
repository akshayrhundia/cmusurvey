package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cmu.model.QuestionAudio;



@Repository("questionAudioDao")
public class QuestionAudioDaoImpl extends AbstractDao<String, QuestionAudio> implements QuestionAudioDao {

	public QuestionAudio findById(String id) {
		QuestionAudio Question = getByKey(id);
		return Question;
	}

	/*public Question findBySSO(String sso) {
		System.out.println("SSO : "+sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		Question Question = (Question)crit.uniqueResult();
		return Question;
	}*/

	@SuppressWarnings("unchecked")
	public List<QuestionAudio> findAllQuestions() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<QuestionAudio> Questions = (List<QuestionAudio>) criteria.list();
		
		return Questions;
	}

	public void save(QuestionAudio Question) {
		persist(Question);
	}

	/*public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		Question Question = (Question)crit.uniqueResult();
		delete(Question);
	}*/

}
