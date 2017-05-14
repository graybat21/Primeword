package com.prime.remember;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class RememberServiceImpl implements RememberService {

	@Inject
	private RememberDAO dao;

	@Override
	public void insertKnownWords(Remember remember) throws Exception {
		dao.insertKnownWords(remember);
	}

	@Override
	public String rememberKnownWords(Remember remember) throws Exception {
		return dao.rememberKnownWords(remember);
	}

	@Override
	public boolean isKnownWords(Remember remember) throws Exception {
		return dao.isKnownWords(remember);
	}

	@Override
	public void updateKnownWords(Remember remember) throws Exception {
		dao.updateKnownWords(remember);
	}

}
