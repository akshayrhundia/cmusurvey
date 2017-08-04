package com.cmu.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminPojo {


	public String getSpeakfirstpage() {
		return speakfirstpage;
	}
	public void setSpeakfirstpage(String speakfirstpage) {
		this.speakfirstpage = speakfirstpage;
	}
	public String getWritefirstpage() {
		return writefirstpage;
	}
	public void setWritefirstpage(String writefirstpage) {
		this.writefirstpage = writefirstpage;
	}
	public String getSpeakIns() {
		return speakIns;
	}
	public void setSpeakIns(String speakIns) {
		this.speakIns = speakIns;
	}
	public String getWriteIns() {
		return writeIns;
	}
	public void setWriteIns(String writeIns) {
		this.writeIns = writeIns;
	}
	public String getLastpage() {
		return lastpage;
	}
	public void setLastpage(String lastpage) {
		this.lastpage = lastpage;
	}
	String speakfirstpage;
	String writefirstpage;
	String speakIns;
	String writeIns;
	String lastpage;

}
