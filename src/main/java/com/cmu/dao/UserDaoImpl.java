package com.cmu.dao;

import com.cmu.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

  @Autowired
  SessionFactory sessionFactory;

  public User findById(int id) {
    User user = getByKey(id);
    return user;
  }

  public User findByuser(String usr) {
    Session session = sessionFactory.openSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<User> cr = cb.createQuery(User.class);
    Root<User> root = cr.from(User.class);
    cr.select(root).where(cb.equal(root.get("userId"), usr));

    Query<User> query = session.createQuery(cr);
    User user = query.uniqueResult();
    return user;
  }

  @SuppressWarnings("unchecked")
  public List<User> findAllUsers() {

    Session session = sessionFactory.openSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<User> cr = cb.createQuery(User.class);
    Root<User> root = cr.from(User.class);
    cr.select(root).orderBy(cb.asc(root.get("id"))).distinct(true);
    Query<User> query = session.createQuery(cr);
    List<User> users = query.getResultList();

    return users;
  }

  public void save(User user) {
    persist(user);
  }

  public void deleteByuser(String usr) {

    delete(findByuser(usr));
  }

}
