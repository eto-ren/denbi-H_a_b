package model;

import java.io.Serializable;

public class User implements Serializable{
	private String name;	//ユーザー名
	private String pass;		//パスワード
	
	public User() { }
	public User(String user, String pass) {
		this.name = user;
		this.pass = pass;
	}
	
	public String getName() { return name; }
	public String getPass() { return pass; }
}
