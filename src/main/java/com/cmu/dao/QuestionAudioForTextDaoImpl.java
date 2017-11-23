package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cmu.model.QuestionAudioForText;



@Repository("questionAudioForTextDao")
public class QuestionAudioForTextDaoImpl extends AbstractDao<String, QuestionAudioForText> implements QuestionAudioForTextDao {

	public QuestionAudioForText findById(String id) {
		QuestionAudioForText Question = getByKey(id);
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
	public List<QuestionAudioForText> findAllQuestions() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<QuestionAudioForText> Questions = (List<QuestionAudioForText>) criteria.list();
		
		return Questions;
	}

	public void save(QuestionAudioForText Question) {
		persist(Question);
	}

	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		QuestionAudioForText Question = (QuestionAudioForText)crit.uniqueResult();
		delete(Question);
	}

}
