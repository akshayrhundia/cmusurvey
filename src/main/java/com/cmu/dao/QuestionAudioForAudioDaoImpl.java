package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cmu.model.QuestionAudioForAudio;
import com.cmu.model.QuestionAudioForAudio;



@Repository("questionAudioForAudioDao")
public class QuestionAudioForAudioDaoImpl extends AbstractDao<String, QuestionAudioForAudio> implements QuestionAudioForAudioDao {

	public QuestionAudioForAudio findById(String id) {
		QuestionAudioForAudio Question = getByKey(id);
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
	public List<QuestionAudioForAudio> findAllQuestions() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<QuestionAudioForAudio> Questions = (List<QuestionAudioForAudio>) criteria.list();
		
		return Questions;
	}

	public void save(QuestionAudioForAudio Question) {
		persist(Question);
	}

	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		QuestionAudioForAudio Question = (QuestionAudioForAudio)crit.uniqueResult();
		delete(Question);
	}

}
