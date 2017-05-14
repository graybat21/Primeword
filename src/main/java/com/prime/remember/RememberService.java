package com.prime.remember;

public interface RememberService {
	public void insertKnownWords(Remember remember) throws Exception;

	public String rememberKnownWords(Remember remember) throws Exception;

	public boolean isKnownWords(Remember remember) throws Exception;

	public void updateKnownWords(Remember remember) throws Exception;
}
