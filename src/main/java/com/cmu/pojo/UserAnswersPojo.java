package com.cmu.pojo;

import com.cmu.model.User;
import com.cmu.model.UserAnswers;
import java.io.Serializable;

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
