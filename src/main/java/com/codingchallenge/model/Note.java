package com.codingchallenge.model;

public class Note {
	private int id;
	private String body;
	
	public Note(int id, String body) {
		this.id = id;
		this.body = body;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getBody() {
		return this.body;
	}
}
