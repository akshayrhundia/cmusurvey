package com.cmu.dao;

import com.cmu.model.QuestionAudioForAudio;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("questionAudioForAudioDao")
public class QuestionAudioForAudioDaoImpl extends AbstractDao<String, QuestionAudioForAudio>
    implements QuestionAudioForAudioDao {

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

  public List<QuestionAudioForAudio> findAllQuestions() {
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<QuestionAudioForAudio> cr = cb.createQuery(QuestionAudioForAudio.class);
    Root<QuestionAudioForAudio> root = cr.from(QuestionAudioForAudio.class);
    cr.select(root).orderBy(cb.asc(root.get("title"))).distinct(true);
    Query<QuestionAudioForAudio> query = session.createQuery(cr);
    return query.getResultList();
  }

  public void save(QuestionAudioForAudio Question) {
    persist(Question);
  }

  public void deleteById(String id) {
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<QuestionAudioForAudio> cr = cb.createQuery(QuestionAudioForAudio.class);
    Root<QuestionAudioForAudio> root = cr.from(QuestionAudioForAudio.class);
    cr.select(root).where(cb.equal(root.get("id"), id));
    Query<QuestionAudioForAudio> query = session.createQuery(cr);
    QuestionAudioForAudio questionAudioForAudio = query.uniqueResult();
    if (questionAudioForAudio != null) {
      delete(questionAudioForAudio);
    }
  }

}
