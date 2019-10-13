package com.cmu.dao;

import com.cmu.model.Admin;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("AdminDao")
public class AdminDaoImpl extends AbstractDao<Integer, Admin> implements AdminDao {

  public Admin findById(int id) {
    Admin Admin = getByKey(id);
    return Admin;
  }

	/*public Admin findBySSO(String sso) {
		System.out.println("SSO : "+sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		Admin Admin = (Admin)crit.uniqueResult();
		return Admin;
	}*/

  @SuppressWarnings("unchecked")
  public List<Admin> findAllAdmins() {

    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Admin> cr = cb.createQuery(Admin.class);
    Root<Admin> root = cr.from(Admin.class);

    Query<Admin> query = session.createQuery(cr);
    return query.getResultList();
  }

  public void save(Admin Admin) {
    persist(Admin);
  }

	/*public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		Admin Admin = (Admin)crit.uniqueResult();
		delete(Admin);
	}*/

}
