package com.cmu.dao;

import com.cmu.model.QuestionText;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("questionTextDao")
public class QuestionTextDaoImpl extends AbstractDao<Integer, QuestionText> implements QuestionTextDao {

  public int getMax() {
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Integer> cr = cb.createQuery(Integer.class);
    Root<QuestionText> root = cr.from(QuestionText.class);
    cr.select(cb.max(root.<Integer>get("id")));

    Query<Integer> query = session.createQuery(cr);
    return query.uniqueResult();
  }

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

    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<QuestionText> cr = cb.createQuery(QuestionText.class);
    Root<QuestionText> root = cr.from(QuestionText.class);
    cr.select(root).orderBy(cb.asc(root.get("title"))).distinct(true);
    Query<QuestionText> query = session.createQuery(cr);
    return query.getResultList();
  }

  public void save(QuestionText Question) {
    persist(Question);
  }
  //public void update(QuestionText Question) {
  //update(Question);
  //}

  public void deleteById(int id) {

    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<QuestionText> cr = cb.createQuery(QuestionText.class);
    Root<QuestionText> root = cr.from(QuestionText.class);
    cr.select(root).where(cb.equal(root.get("id"), id));
    Query<QuestionText> query = session.createQuery(cr);

    QuestionText Question = query.uniqueResult();
    if (Question != null) {
      delete(Question);
    }
  }

  public void updateTitle(QuestionText Question) {
    Session session = getSession();
    QuestionText entity = session.get(QuestionText.class, Question.getId());//findById(Question.getId());
    session.evict(entity);
    if (entity != null) {
      System.out.println(entity.getTitle());
      System.out.println(Question.getTitle());
      entity.setTitle(Question.getTitle());
    }

    session.saveOrUpdate(entity);
    //update(Question);

  }

}
