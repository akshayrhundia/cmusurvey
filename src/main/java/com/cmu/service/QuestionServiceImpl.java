package com.cmu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmu.dao.QuestionDao;
import com.cmu.model.Question;


@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionDao dao;

	public Question findById(int id) {
		return dao.findById(id);
	}

	/*public Question findBySSO(String sso) {
		Question Question = dao.findBySSO(sso);
		return Question;
	}*/

	public void saveQuestion(Question Question) {
		dao.save(Question);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateQuestion(Question Question) {
		Question entity = dao.findById(Question.getId());
		/*if(entity!=null){
			entity.setSsoId(Question.getSsoId());
			entity.setFirstName(Question.getFirstName());
			entity.setLastName(Question.getLastName());
			entity.setEmail(Question.getEmail());
			entity.setQuestionDocuments(Question.getQuestionDocuments());
		}*/
	}

	
	/*public void deleteQuestionBySSO(String sso) {
		dao.deleteBySSO(sso);
	}*/

	public List<Question> findAllQuestions() {
		return dao.findAllQuestions();
	}

	/*public boolean isQuestionSSOUnique(Integer id, String sso) {
		Question Question = findBySSO(sso);
		return ( Question == null || ((id != null) && (Question.getId() == id)));
	}*/
	
}
