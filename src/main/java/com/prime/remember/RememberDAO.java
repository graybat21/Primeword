package com.prime.remember;

public interface RememberDAO {
	public void insertKnownWords(Remember remember);

	public String rememberKnownWords(Remember remember);

	public boolean isKnownWords(Remember remember);

	public void updateKnownWords(Remember remember);
}
