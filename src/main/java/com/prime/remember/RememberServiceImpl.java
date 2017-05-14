package com.prime.remember;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class RememberServiceImpl implements RememberService {

	@Inject
	private RememberDAO dao;

	@Override
	public void recordSession(Remember remember) throws Exception {
		dao.recordSession(remember);
	}

}
