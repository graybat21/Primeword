package com.prime.user;

public class User {

	private int no;
	private String username;
	private String passwd;
	private String birth;
	private String phone;
	private String belong;

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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	@Override
	public String toString() {
		return "\nUser [no=" + no + ", username=" + username + ", passwd=" + passwd + ", birth=" + birth + ", phone="
				+ phone + ", belong=" + belong + "]";
	}

}
