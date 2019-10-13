package com.cmu.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "USER_answers")
public class UserAnswers implements Serializable {

  @Id
  @Column(name = "qId")
  private Integer qId;

  @Id
  @Column(name = "userId")
  private String userId;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Id
  @Column(name = "type")
  private String type;

  @Id
  @Column(name = "qtype")
  private String qtype;

  public String getQtype() {
    return qtype;
  }

  public void setQtype(String qtype) {
    this.qtype = qtype;
  }

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "reply", nullable = false)
  private byte[] reply;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "que", nullable = false)
  private byte[] que;

  public byte[] getQue() {
    return que;
  }

  public void setQue(byte[] que) {
    this.que = que;
  }

  public Integer getqId() {
    return qId;
  }

  public void setqId(Integer qId) {
    this.qId = qId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public byte[] getReply() {
    return reply;
  }

  public void setReply(byte[] reply) {
    this.reply = reply;
  }

}
