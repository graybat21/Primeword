package com.prime.admin;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prime.study.Study;
import com.prime.user.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO adminDao;

	@Transactional
	@Override
	public List<User> userList() throws Exception {
		return adminDao.userList();
	}

	@Override
	public void userDelete(String username) throws Exception {
		adminDao.userDelete(username);
	}

	@Override
	public List<Study> wordsList(Map<String, Object> map) throws Exception {
		return adminDao.wordsList(map);
	}

}
