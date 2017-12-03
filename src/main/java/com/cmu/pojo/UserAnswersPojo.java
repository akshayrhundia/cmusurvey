package com.cmu.pojo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cmu.model.User;
import com.cmu.model.UserAnswers;

public class UserAnswersPojo implements Serializable {

	public UserAnswers getAns() {
		return ans;
	}

	public void setAns(UserAnswers ans) {
		this.ans = ans;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private UserAnswers ans;
	
	private User user;

	

}
