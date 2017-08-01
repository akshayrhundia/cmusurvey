package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cmu.model.UserAnswers;



@Repository("UserAnswersDao")
public class UserAnswersDaoImpl extends AbstractDao<Integer, UserAnswers> implements UserAnswersDao {

	
	/*public UserAnswers findBySSO(String sso) {
		System.out.println("SSO : "+sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		UserAnswers UserAnswers = (UserAnswers)crit.uniqueResult();
		return UserAnswers;
	}*/

	@SuppressWarnings("unchecked")
	public List<UserAnswers> findAllUserAnswers(String userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<UserAnswers> UserAnswerss = (List<UserAnswers>) criteria.list();
		
		return UserAnswerss;
	}

	public void save(UserAnswers UserAnswers) {
		persist(UserAnswers);
	}

	@Override
	public UserAnswers findUserAnswerByQuestionId(int qId, String userId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("qId", qId));
		crit.add(Restrictions.eq("userId", userId));
		UserAnswers UserAnswers = (UserAnswers)crit.uniqueResult();
		return UserAnswers;
	}

	@Override
	public void deleteUserAnswerById(Integer qId, String userId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("qId", qId));
		crit.add(Restrictions.eq("userId", userId));
		UserAnswers UserAnswers = (UserAnswers)crit.uniqueResult();
		delete(UserAnswers);
		
	}

	
	/*public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		UserAnswers UserAnswers = (UserAnswers)crit.uniqueResult();
		delete(UserAnswers);
	}*/

}
