package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cmu.model.Admin;



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
		Criteria criteria = createEntityCriteria();
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Admin> Admins = (List<Admin>) criteria.list();
		
		return Admins;
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
