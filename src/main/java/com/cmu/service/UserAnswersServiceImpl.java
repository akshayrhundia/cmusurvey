package com.cmu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmu.dao.UserAnswersDao;
import com.cmu.model.UserAnswers;


@Service("UserAnswersService")
@Transactional
public class UserAnswersServiceImpl implements UserAnswersService{

	@Autowired
	private UserAnswersDao dao;

	

	/*public UserAnswers findBySSO(String sso) {
		UserAnswers UserAnswers = dao.findBySSO(sso);
		return UserAnswers;
	}*/

	public void saveUserAnswer(UserAnswers UserAnswers) {
		dao.save(UserAnswers);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUserAnswer(UserAnswers UserAnswers) {
		UserAnswers entity = dao.findUserAnswerByQuestionId(UserAnswers.getqId(),UserAnswers.getUserId());
		/*if(entity!=null){
			entity.setSsoId(UserAnswers.getSsoId());
			entity.setFirstName(UserAnswers.getFirstName());
			entity.setLastName(UserAnswers.getLastName());
			entity.setEmail(UserAnswers.getEmail());
			entity.setUserAnswersDocuments(UserAnswers.getUserAnswersDocuments());
		}*/
	}

	
	/*public void deleteUserAnswersBySSO(String sso) {
		dao.deleteBySSO(sso);
	}*/

	public List<UserAnswers> findAllUserAnswers(String userId) {
		return dao.findAllUserAnswers(userId);
	}

	@Override
	public UserAnswers findUserAnswerByQuestionId(int id, String userId) {
		// TODO Auto-generated method stub
		return dao.findUserAnswerByQuestionId(id, userId);
	}

	@Override
	public void deleteUserAnswerById(Integer qId, String userId) {
		dao.deleteUserAnswerById(qId, userId);
		
	}

	/*public boolean isUserAnswersSSOUnique(Integer id, String sso) {
		UserAnswers UserAnswers = findBySSO(sso);
		return ( UserAnswers == null || ((id != null) && (UserAnswers.getId() == id)));
	}*/
	
}
