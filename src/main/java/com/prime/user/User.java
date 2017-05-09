package com.prime.user;

public class User {

	private String username;
	private String passwd;
	private int grade;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", passwd=" + passwd + ", grade=" + grade + "]";
	}

}
