package com.cmu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmu.dao.QuestionAudioForAudioDao;
import com.cmu.dao.QuestionAudioForTextDao;
import com.cmu.dao.QuestionCountAudioForAudioDao;
import com.cmu.dao.QuestionCountAudioForTextDao;
import com.cmu.dao.QuestionTextDao;
import com.cmu.model.CountAudioForAudio;
import com.cmu.model.CountAudioForText;
import com.cmu.model.QuestionAudioForAudio;
import com.cmu.model.QuestionAudioForText;
import com.cmu.model.QuestionText;


@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionAudioForAudioDao daoAudioForAudio;
	
	@Autowired
	private QuestionAudioForTextDao daoAudioForText;
	
	@Autowired
	private QuestionTextDao daoText;
	
	@Autowired
	private QuestionCountAudioForTextDao daoCountAudioForText;
	
	@Autowired
	private QuestionCountAudioForAudioDao daoCountAudioForAudio;
	

	public QuestionText findTextById(int id) {
		//return daoText.findById(id);
		int max=daoText.getMax();
		QuestionText ret=null;
		System.out.println("---Enter---"+max);
		while(id<=max && ret==null){
			ret=daoText.findById(id);
			System.out.println(id);
			id++;
		}
		return ret;
	}

	/*public Question findBySSO(String sso) {
		Question Question = dao.findBySSO(sso);
		return Question;
	}*/

	public void saveTextQuestion(QuestionText Question) {
		daoText.save(Question);
	}
	/*public void updateTextQuestion(QuestionText Question) {
		daoText.update((Question);
	}*/

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateTextQuestionTitle(QuestionText Question) {
		/*QuestionText entity = daoText.findById(Question.getId());
		if(entity!=null){
			entity.setTitle(Question.getTitle());
		}*/
		daoText.updateTitle(Question);
	}

	
	/*public void deleteQuestionBySSO(String sso) {
		dao.deleteBySSO(sso);
	}*/

	public List<QuestionText> findAllTextQuestions() {
		return daoText.findAllQuestions();
	}

	public QuestionAudioForText findAudioForTextById(String id) {
		String[] sp=id.split("_");
		int cnt=Integer.parseInt(sp[1]);
		int max=daoCountAudioForText.getMax();
		QuestionAudioForText ret=null;
		while(cnt<=max && ret==null){
			id="AUDIOTEXT_"+cnt;
			ret=daoAudioForText.findById(id);
			cnt++;
		}
		return ret;
	}

	public QuestionAudioForAudio findAudioForAudioById(String id) {
		String[] sp=id.split("_");
		int cnt=Integer.parseInt(sp[1]);
		int max=daoCountAudioForAudio.getMax();
		QuestionAudioForAudio ret=null;
		while(cnt<=max && ret==null){
			id="AUDIOAUDIO_"+cnt;
			ret=daoAudioForAudio.findById(id);
			cnt++;
		}
		return ret;
	}
	/*public Question findBySSO(String sso) {
		Question Question = dao.findBySSO(sso);
		return Question;
	}*/

	public void saveAudioQuestionForText(QuestionAudioForText Question) {
		daoAudioForText.save(Question);
		daoCountAudioForText.save(new CountAudioForText());
	}

	public void saveAudioQuestionForAudio(QuestionAudioForAudio Question) {
		daoAudioForAudio.save(Question);
		daoCountAudioForAudio.save(new CountAudioForAudio());
	}

	
	
	/*public void deleteQuestionBySSO(String sso) {
		dao.deleteBySSO(sso);
	}*/

	public List<QuestionAudioForText> findAllAudioForTextQuestions() {
		return daoAudioForText.findAllQuestions();
	}

	public List<QuestionAudioForAudio> findAllAudioForAudioQuestions() {
		return daoAudioForAudio.findAllQuestions();
	}

	@Override
	public void deleteTextById(int id) {
		daoText.deleteById(id);
		
	}

	@Override
	public void deleteAudioForAudioById(String id) {
		daoAudioForAudio.deleteById(id);
		
	}
	@Override
	public void deleteAudioForTextById(String id) {
		daoAudioForText.deleteById(id);
		
	}


	
}
