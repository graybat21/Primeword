package com.prime.user;

public interface UserDAO {
	public void insert(User user); //

	public User userLogin(User user);

	public int userExist(int no);

	public void userDelete(int no);
	
}
