package com.prime.remember;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class RememberDAOImpl implements RememberDAO {
	private static String namespace = "com.prime.mappers.studyMapper";
	@Inject
	private SqlSession session;

	@Override
	public void recordSession(Remember remember) {
		session.insert(namespace + ".recordSession", remember);
	}

}
