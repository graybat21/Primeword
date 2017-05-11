package com.prime.study;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class StudyServiceImpl implements StudyService {

	@Inject
	private StudyDAO dao;

	@Override
	public List<Study> wordList(Study study) throws Exception {
		return dao.wordList(study);
	}

	@Override
	public int howManyLesson(Study study) throws Exception {
		return dao.howManyLesson(study);
	}

	@Override
	public List<String> textbookListByGrade(Study study) throws Exception {
		return dao.textbookListByGrade(study);
	}

}
