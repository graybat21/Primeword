package com.prime.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.prime.remember.Remember;
import com.prime.study.Study;
import com.prime.user.User;

@Repository
public class AdminDAOImpl implements AdminDAO {
	private static String studyNamespace = "com.prime.mappers.studyMapper";
	private static String userNamespace = "com.prime.mappers.userMapper";
	private static String rememberNamespace = "com.prime.mappers.rememberMapper";

	@Inject
	private SqlSession session;

	@Override
	public List<User> userList(HashMap<String, Object> map) {
		return session.selectList(userNamespace + ".userList", map);
	}

	@Override
	public int userListCnt(HashMap<String, Object> map) {
		return session.selectOne(userNamespace + ".userListCnt", map);
	}

	@Override
	public void userDelete(int no) {
		session.selectOne(userNamespace + ".userDelete", no);
	}

	@Override
	public User userDetail(int no) {
		return session.selectOne(userNamespace + ".userDetail", no);
	}

	@Override
	public List<Study> wordsList(HashMap<String, Object> map) {
		return session.selectList(studyNamespace + ".wordsList", map);
	}

	@Override
	public int wordsListCnt(HashMap<String, Object> map) {
		return session.selectOne(studyNamespace + ".wordsListCnt", map);
	}

	@Override
	public void wordDelete(int no) {
		session.delete(studyNamespace + ".wordDelete", no);
	}

	@Override
	public List<Remember> knownWordsByUserNo(int user_no) {
		return session.selectList(rememberNamespace+".knownWordsByUserNo", user_no);
	}
	
	

//	@Override
//	public List<String> gradeList() {
//		return session.selectList(studyNamespace + ".gradeList");
//	}
//
//	@Override
//	public List<String> textbookList(Map<String, Object> map) {
//		return session.selectList(studyNamespace + ".textbookList", map);
//	}
//
//	@Override
//	public List<String> lessonList(Map<String, Object> map) {
//		return session.selectList(studyNamespace + ".lessonList", map);
//	}


}
