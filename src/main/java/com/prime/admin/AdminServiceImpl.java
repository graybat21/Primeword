package com.prime.admin;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prime.user.User;
import com.prime.user.UserDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private UserDAO userDao;

	@Transactional
	@Override
	public List<User> userList() throws Exception {
		return userDao.userList();
	}

	@Override
	public void userDelete(String username) throws Exception {
		userDao.userDelete(username);
	}

}
