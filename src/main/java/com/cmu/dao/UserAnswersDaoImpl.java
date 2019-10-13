package com.cmu.dao;

import com.cmu.model.UserAnswers;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("UserAnswersDao")
public class UserAnswersDaoImpl extends AbstractDao<Integer, UserAnswers> implements UserAnswersDao {

  @Autowired
  SessionFactory sessionFactory;

	/*public UserAnswers findBySSO(String sso) {
		System.out.println("SSO : "+sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		UserAnswers UserAnswers = (UserAnswers)crit.uniqueResult();
		return UserAnswers;
	}*/

  @SuppressWarnings("unchecked")
  public List<UserAnswers> findAllUserAnswers(String userId) {
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<UserAnswers> cr = cb.createQuery(UserAnswers.class);
    Root<UserAnswers> root = cr.from(UserAnswers.class);
    cr.select(root).where(cb.equal(root.get("userId"), userId));
    Query<UserAnswers> query = session.createQuery(cr);
    return query.getResultList();
  }

  public void save(UserAnswers UserAnswers) {
    persist(UserAnswers);
  }

  public UserAnswers findUserAnswerByQuestionId(int qId, String userId) {

    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<UserAnswers> cr = cb.createQuery(UserAnswers.class);
    Root<UserAnswers> root = cr.from(UserAnswers.class);

    Predicate[] predicates = new Predicate[2];
    predicates[0] = cb.equal(root.get("userId"), userId);
    predicates[1] = cb.equal(root.get("qId"), qId);
    cr.select(root).where(predicates);
    Query<UserAnswers> query = session.createQuery(cr);
    return query.uniqueResult();
  }

  public void deleteUserAnswerById(Integer qId, String userId) {
    UserAnswers UserAnswers = findUserAnswerByQuestionId(qId, userId);
    if (UserAnswers != null) {
      delete(UserAnswers);
    }
  }

  public List<UserAnswers> findAllUserAnswersByQuestionType(String type) {
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<UserAnswers> cr = cb.createQuery(UserAnswers.class);
    Root<UserAnswers> root = cr.from(UserAnswers.class);
    cr.select(root).where(cb.equal(root.get("qtype"), type));
    Query<UserAnswers> query = session.createQuery(cr);
    return query.getResultList();
  }

	
	/*public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		UserAnswers UserAnswers = (UserAnswers)crit.uniqueResult();
		delete(UserAnswers);
	}*/

}
