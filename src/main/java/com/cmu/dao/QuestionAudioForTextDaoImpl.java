package com.cmu.dao;

import com.cmu.model.QuestionAudioForText;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("questionAudioForTextDao")
public class QuestionAudioForTextDaoImpl extends AbstractDao<String, QuestionAudioForText>
    implements QuestionAudioForTextDao {

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
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<QuestionAudioForText> cr = cb.createQuery(QuestionAudioForText.class);
    Root<QuestionAudioForText> root = cr.from(QuestionAudioForText.class);
    cr.select(root).orderBy(cb.asc(root.get("title"))).distinct(true);
    Query<QuestionAudioForText> query = session.createQuery(cr);
    return query.getResultList();
  }

  public void save(QuestionAudioForText Question) {
    persist(Question);
  }

  public void deleteById(String id) {
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<QuestionAudioForText> cr = cb.createQuery(QuestionAudioForText.class);
    Root<QuestionAudioForText> root = cr.from(QuestionAudioForText.class);
    cr.select(root).where(cb.equal(root.get("id"), id));
    Query<QuestionAudioForText> query = session.createQuery(cr);
    QuestionAudioForText questionAudioForText = query.uniqueResult();
    if (questionAudioForText != null) {
      delete(questionAudioForText);
    }
  }

}
