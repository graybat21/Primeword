package com.prime.admin;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.prime.study.Study;
import com.prime.user.User;

@Repository
public class AdminDAOImpl implements AdminDAO {
	private static String studyNamespace = "com.prime.mappers.studyMapper";
	private static String userNamespace = "com.prime.mappers.userMapper";

	@Inject
	private SqlSession session;

	@Override
	public List<User> userList() {
		return session.selectList(userNamespace + ".userList");
	}

	@Override
	public void userDelete(String username) {
		session.selectOne(userNamespace + ".userDelete", username);
	}

	@Override
	public List<Study> wordsList(Map<String, Object> map) {
		return session.selectList(studyNamespace + ".wordsList", map);
	}

}
