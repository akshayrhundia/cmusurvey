package com.cmu.model;

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

@Entity
@Table(name = "Admin")
public class Admin {

	

	public Admin(){
		speakfirstpage="Empty".getBytes();
		writefirstpage="Empty".getBytes();
		speakIns="Empty".getBytes();
		writeIns="Empty".getBytes();
		lastpage="Empty".getBytes();
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
