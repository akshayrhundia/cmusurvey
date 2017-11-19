package com.cmu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmu.dao.QuestionAudioDao;
import com.cmu.dao.QuestionCountDao;
import com.cmu.dao.QuestionTextDao;
import com.cmu.model.Count;
import com.cmu.model.QuestionAudio;
import com.cmu.model.QuestionText;


@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionAudioDao daoAudio;
	
	@Autowired
	private QuestionTextDao daoText;
	
	@Autowired
	private QuestionCountDao daoCount;
	

	public QuestionText findTextById(int id) {
		return daoText.findById(id);
	}

	/*public Question findBySSO(String sso) {
		Question Question = dao.findBySSO(sso);
		return Question;
	}*/

	public void saveTextQuestion(QuestionText Question) {
		daoText.save(Question);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateTextQuestion(QuestionText Question) {
		QuestionText entity = daoText.findById(Question.getId());
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

	public List<QuestionText> findAllTextQuestions() {
		return daoText.findAllQuestions();
	}

	public QuestionAudio findAudioById(String id) {
		String[] sp=id.split("_");
		int cnt=Integer.parseInt(sp[1]);
		int max=daoCount.getMax();
		QuestionAudio ret=null;
		while(cnt<max && ret==null){
			id="AUDIO_"+cnt;
			ret=daoAudio.findById(id);
			cnt++;
		}
		return ret;
	}

	/*public Question findBySSO(String sso) {
		Question Question = dao.findBySSO(sso);
		return Question;
	}*/

	public void saveAudioQuestion(QuestionAudio Question) {
		daoAudio.save(Question);
		daoCount.save(new Count());
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateAudioQuestion(QuestionAudio Question) {
		QuestionAudio entity = daoAudio.findById(Question.getId());
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

	public List<QuestionAudio> findAllAudioQuestions() {
		return daoAudio.findAllQuestions();
	}


	
}
