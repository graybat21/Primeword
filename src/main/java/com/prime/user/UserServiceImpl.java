package com.prime.user;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;

	@Transactional
	@Override
	public void insert(User user) throws Exception {
		dao.insert(user);
	}

	@Transactional
	@Override
	public User userLogin(User user) throws Exception {
		return dao.userLogin(user);
	}

}
