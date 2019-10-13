package com.cmu.dao;

import com.cmu.model.UserDocument;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao {

  @SuppressWarnings("unchecked")
  public List<UserDocument> findAll() {

    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<UserDocument> cr = cb.createQuery(UserDocument.class);
    Query<UserDocument> query = session.createQuery(cr);
    return query.getResultList();
  }

  public void save(UserDocument document) {
    persist(document);
  }

  public UserDocument findById(int id) {
    return getByKey(id);
  }

  @SuppressWarnings("unchecked")
  public List<UserDocument> findAllByUserId(int userId) {

    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<UserDocument> cr = cb.createQuery(UserDocument.class);
    Root<UserDocument> root = cr.from(UserDocument.class);
    cr.select(root).where(cb.equal(root.get("id"), userId));
    Query<UserDocument> query = session.createQuery(cr);
    return query.getResultList();
  }

  public void deleteById(int id) {
    UserDocument document = getByKey(id);
    delete(document);
  }

}
