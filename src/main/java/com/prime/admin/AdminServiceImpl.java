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

	@Override
	public List<String> gradeList() throws Exception {
		return adminDao.gradeList();
	}

	@Override
	public List<String> textbookList(Map<String, Object> map) throws Exception {
		return adminDao.textbookList(map);
	}

	@Override
	public List<String> lessonList(Map<String, Object> map) throws Exception {
		return adminDao.lessonList(map);
	}

	@Override
	public List<Study> adminWordsListBySearch(Map<String, Object> map) throws Exception {
		return adminDao.adminWordsListBySearch(map);
	}

}
