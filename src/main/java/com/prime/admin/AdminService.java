package com.prime.admin;

import java.util.List;

import com.prime.user.User;

public interface AdminService {

	public List<User> userList() throws Exception;
	
	public void userDelete(String username) throws Exception;

}
