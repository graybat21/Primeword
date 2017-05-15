package com.prime.remember;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class RememberDAOImpl implements RememberDAO {
	private static String namespace = "com.prime.mappers.rememberMapper";
	@Inject
	private SqlSession session;

	@Override
	public void insertKnownWords(Remember remember) {
		session.insert(namespace + ".insertKnownWords", remember);
	}

	@Override
	public String rememberKnownWords(Remember remember) {
		return session.selectOne(namespace + ".rememberKnownWords", remember);
	}

	@Override
	public boolean isKnownWords(Remember remember) {
		return session.selectOne(namespace + ".isKnownWords", remember);
	}

	@Override
	public void updateKnownWords(Remember remember) {
		session.update(namespace + ".updateKnownWords", remember);
	}

	@Override
	public void wordsInitialize(Remember remember) {
		session.update(namespace + ".wordsInitialize", remember);
	}

}
