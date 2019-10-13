package com.cmu.dao;

import com.cmu.model.CountAudioForText;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("questionCountAudioForTextDao")
public class QuestionCountAudioForTextDaoImpl extends AbstractDao<Integer, CountAudioForText>
    implements QuestionCountAudioForTextDao {

  public void save(CountAudioForText cnt) {
    persist(cnt);
  }

  public int getMax() {
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Integer> cr = cb.createQuery(Integer.class);
    Root<CountAudioForText> root = cr.from(CountAudioForText.class);
    cr.select(cb.max(root.<Integer>get("id")));

    Query<Integer> query = session.createQuery(cr);
    return query.uniqueResult();
  }

}
