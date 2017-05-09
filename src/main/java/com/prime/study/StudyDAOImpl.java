package com.prime.study;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class StudyDAOImpl implements StudyDAO {
	private static String namespace = "com.prime.mappers.studyMapper";

	@Inject
	private SqlSession session;

	@Override
	public List<Study> wordList(Study study) {
		return session.selectList(namespace + ".wordList", study);
	}

	@Override
	public int howManyLesson(Study study) {
		return session.selectOne(namespace + ".howManyLesson", study);
	}

}
