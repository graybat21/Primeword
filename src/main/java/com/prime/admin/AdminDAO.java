package com.prime.admin;

import java.util.List;
import java.util.Map;

import com.prime.study.Study;
import com.prime.user.User;

public interface AdminDAO {
	public List<User> userList();

	public void userDelete(String username);

	public List<Study> wordsList(Map<String, Object> map);

	public List<String> gradeList();
	public List<String> textbookList(Map<String, Object> map);
	public List<String> lessonList(Map<String, Object> map);
	
	public List<Study> adminWordsListBySearch(Map<String, Object> map);
}
