package com.prime.user;

import java.util.List;

public interface UserService {
	public void insert(User user) throws Exception;

	public User userLogin(User user) throws Exception;

	public int userExist(String username) throws Exception;

	public List<String> belongList() throws Exception;

}
