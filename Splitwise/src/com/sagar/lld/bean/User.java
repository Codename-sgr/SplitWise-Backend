package com.sagar.lld.bean;

public class User {
	private String id;
	private String name;
	private String mailId;
	private String mobile;
	
	public User(String id, String name, String mailId, String mobile) {
		this.id = id;
		this.name = name;
		this.mailId = mailId;
		this.mobile = mobile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
