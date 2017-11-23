package com.cmu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cmu.model.CountAudioForText;
import com.cmu.model.QuestionAudioForText;
import com.cmu.model.QuestionText;



@Repository("questionTextDao")
public class QuestionTextDaoImpl extends AbstractDao<Integer, QuestionText> implements QuestionTextDao {

	@Override
	public int getMax() {
		Criteria criteria = getSession().createCriteria(QuestionText.class).setProjection(Projections.max("id"));
		Integer max = (Integer) criteria.uniqueResult();
		return max;
		/*String maxHql = "Select max(id) FROM question_text";
        
        Query maxQuery = getSession().createQuery(maxHql);
        System.out
                .println("Maximum salary in list : " + maxQuery.list().get(0));
        return (Integer) maxQuery.list().get(0);*/
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
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<QuestionText> Questions = (List<QuestionText>) criteria.list();
		
		return Questions;
	}

	public void save(QuestionText Question) {
		persist(Question);
	}
	//public void update(QuestionText Question) {
		//update(Question);
	//}

	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		QuestionText Question = (QuestionText)crit.uniqueResult();
		delete(Question);
	}


	@Override
	public void updateTitle(QuestionText Question) {
		Session session=getSession();
		QuestionText entity = (QuestionText) session.get(QuestionText.class, Question.getId());//findById(Question.getId());
		session.evict(entity);
		if(entity!=null){
			System.out.println(entity.getTitle());
			System.out.println(Question.getTitle());
			entity.setTitle(Question.getTitle());
		}
		
		session.saveOrUpdate(entity);
		//update(Question);
		
	}

}
