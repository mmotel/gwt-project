package com.geekblog.page.shared.models;

public class BlogUser {

	private String login;
	private String passwd;
	private Integer id;
	
	public BlogUser() {
	}
	public BlogUser(String login, String passwd) {
		super();
		this.login = login;
		this.passwd = passwd;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
