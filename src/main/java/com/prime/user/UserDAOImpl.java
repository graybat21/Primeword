package com.prime.user;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	private static String namespace = "com.prime.mappers.userMapper";

	@Inject
	private SqlSession session;

	@Override
	public void insert(User user) {
		session.insert(namespace + ".insert", user);
	}

	@Override
	public User userLogin(User user) {
		return session.selectOne(namespace + ".userLogin", user);
	}

	@Override
	public int userExist(String username) {
		return session.selectOne(namespace + ".userExist", username);
	}

	@Override
	public List<User> userList() {
		return session.selectList(namespace + ".userList");
	}

	@Override
	public void userDelete(String username) {
		session.delete(namespace + ".userDelete", username);
	}

}