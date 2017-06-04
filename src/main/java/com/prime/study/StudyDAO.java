package com.prime.study;

import java.util.List;

public interface StudyDAO {
	public List<Study> wordList(Study study);

	public int howManyLesson(Study study);
	public List<String> textbookListByGrade(Study study);
	public Integer getCreatorForTextbookList(String belong);
}
