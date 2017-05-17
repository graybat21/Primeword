package com.prime.user;

import java.util.List;

public interface UserDAO {
	public void insert(User user); //

	public User userLogin(User user);

	public int userExist(String username);

	public List<User> userList();

	public void userDelete(String username);
}
