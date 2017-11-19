package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cmu.model.QuestionText;



@Repository("questionTextDao")
public class QuestionTextDaoImpl extends AbstractDao<Integer, QuestionText> implements QuestionTextDao {

	public QuestionText findById(int id) {
		QuestionText Question = getByKey(id);
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
	public List<QuestionText> findAllQuestions() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<QuestionText> Questions = (List<QuestionText>) criteria.list();
		
		return Questions;
	}

	public void save(QuestionText Question) {
		persist(Question);
	}

	/*public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		Question Question = (Question)crit.uniqueResult();
		delete(Question);
	}*/

}
