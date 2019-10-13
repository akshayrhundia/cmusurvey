package com.cmu.dao;

import com.cmu.model.CountAudioForAudio;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("questionCountAudioForAudioDao")
public class QuestionCountAudioForAudioDaoImpl extends AbstractDao<Integer, CountAudioForAudio>
    implements QuestionCountAudioForAudioDao {

  public void save(CountAudioForAudio cnt) {
    persist(cnt);
  }

  public int getMax() {

    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Integer> cr = cb.createQuery(Integer.class);
    Root<CountAudioForAudio> root = cr.from(CountAudioForAudio.class);
    cr.select(cb.max(root.<Integer>get("id")));

    Query<Integer> query = session.createQuery(cr);
    return query.uniqueResult();
  }

}
