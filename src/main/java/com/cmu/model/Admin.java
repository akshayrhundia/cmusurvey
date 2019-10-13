package com.cmu.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin {

  public byte[] getSpeakAudioIns() {
    return speakAudioIns;
  }

  public void setSpeakAudioIns(byte[] speakAudioIns) {
    this.speakAudioIns = speakAudioIns;
  }

  public byte[] getWriteAudioIns() {
    return writeAudioIns;
  }

  public void setWriteAudioIns(byte[] writeAudioIns) {
    this.writeAudioIns = writeAudioIns;
  }

  public Admin() {
    speakfirstpage = "Empty".getBytes();
    writefirstpage = "Empty".getBytes();
    speakIns = "Empty".getBytes();
    writeIns = "Empty".getBytes();
    lastpage = "Empty".getBytes();
    speakAudioIns = "Empty".getBytes();
    writeAudioIns = "Empty".getBytes();
  }

  public byte[] getSpeakfirstpage() {
    return speakfirstpage;
  }

  public void setSpeakfirstpage(byte[] speakfirstpage) {
    this.speakfirstpage = speakfirstpage;
  }

  public byte[] getWritefirstpage() {
    return writefirstpage;
  }

  public void setWritefirstpage(byte[] writefirstpage) {
    this.writefirstpage = writefirstpage;
  }

  public byte[] getSpeakIns() {
    return speakIns;
  }

  public void setSpeakIns(byte[] speakIns) {
    this.speakIns = speakIns;
  }

  public byte[] getWriteIns() {
    return writeIns;
  }

  public void setWriteIns(byte[] writeIns) {
    this.writeIns = writeIns;
  }

  public byte[] getLastpage() {
    return lastpage;
  }

  public void setLastpage(byte[] lastpage) {
    this.lastpage = lastpage;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "speakfirstpage", nullable = false)
  private byte[] speakfirstpage;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "writefirstpage", nullable = false)
  private byte[] writefirstpage;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "speakIns", nullable = false)
  private byte[] speakIns;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "writeIns", nullable = false)
  private byte[] writeIns;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "speakAudioIns", nullable = true)
  private byte[] speakAudioIns;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "writeAudioIns", nullable = true)
  private byte[] writeAudioIns;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "secondlastpage", nullable = false)
  private byte[] secondlastpage;

  public byte[] getSecondlastpage() {
    return secondlastpage;
  }

  public void setSecondlastpage(byte[] secondlastpage) {
    this.secondlastpage = secondlastpage;
  }

  @Lob
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "lastpage", nullable = false)
  private byte[] lastpage;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}
