package com.prime.admin;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.prime.remember.Remember;
import com.prime.study.Study;
import com.prime.user.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO adminDao;

	@Override
	public List<User> userList(HashMap<String, Object> map) throws Exception {
		return adminDao.userList(map);
	}

	@Override
	public int userListCnt(HashMap<String, Object> map) throws Exception {
		return adminDao.userListCnt(map);
	}

	@Override
	public void userDelete(int no) throws Exception {
		adminDao.userDelete(no);
	}

	@Override
	public User userDetail(int no) throws Exception {
		return adminDao.userDetail(no);
	}

	@Override
	public List<Study> wordsList(HashMap<String, Object> map) throws Exception {
		return adminDao.wordsList(map);
	}

	@Override
	public int wordsListCnt(HashMap<String, Object> map) throws Exception {
		return adminDao.wordsListCnt(map);
	}

	@Override
	public void wordDelete(int no) throws Exception {
		adminDao.wordDelete(no);
	}

	@Override
	public void wordInsert(Study study) throws Exception {
		adminDao.wordInsert(study);
	}

	@Override
	public List<Remember> knownWordsByUserNo(int user_no) throws Exception {
		return adminDao.knownWordsByUserNo(user_no);
	}

	@Override
	public int wordsGroupListCnt(HashMap<String, Object> map) throws Exception {
		return adminDao.wordsGroupListCnt(map);
	}

	@Override
	public List<Study> wordsGroupList(HashMap<String, Object> map) throws Exception {
		return adminDao.wordsGroupList(map);
	}

	@Override
	public void wordsGroupDelete(Study study) throws Exception {
		adminDao.wordsGroupDelete(study);
	}

	
	// @Override
	// public List<String> gradeList() throws Exception {
	// return adminDao.gradeList();
	// }
	//
	// @Override
	// public List<String> textbookList(Map<String, Object> map) throws
	// Exception {
	// return adminDao.textbookList(map);
	// }
	//
	// @Override
	// public List<String> lessonList(Map<String, Object> map) throws Exception
	// {
	// return adminDao.lessonList(map);
	// }

}
