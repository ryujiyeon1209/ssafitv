package com.ssafit.rh.model.dto;

import java.util.Date;

public class Calender {
	
	private String date;
	private String coment;
	private String mood;
	private String userId;
	
	public Calender() {}
	
	public Calender(String date, String coment, String mood, String userId) {
		super();
		this.date = date;
		this.coment = coment;
		this.mood = mood;
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Calender [date=" + date + ", coment=" + coment + ", mood=" + mood + ", userId=" + userId + "]";
	}
	
}
