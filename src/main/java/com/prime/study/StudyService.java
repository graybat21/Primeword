package com.prime.study;

import java.util.List;

public interface StudyService {

	public List<Study> wordList(Study study) throws Exception;

	public int howManyLesson(Study study) throws Exception;

	public List<String> textbookListByGrade(Study study) throws Exception;

	public Integer getCreatorForTextbookList(String belong) throws Exception;
}
