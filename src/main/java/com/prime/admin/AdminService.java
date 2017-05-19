package com.prime.admin;

import java.util.List;
import java.util.Map;

import com.prime.study.Study;
import com.prime.user.User;

public interface AdminService {

	public List<User> userList() throws Exception;

	public void userDelete(String username) throws Exception;

	public List<Study> wordsList(Map<String, Object> map) throws Exception;
	
	public List<String> gradeList()throws Exception;
	public List<String> textbookList(Map<String, Object> map)throws Exception;
	public List<String> lessonList(Map<String, Object> map)throws Exception;
	
	public List<Study> adminWordsListBySearch(Map<String, Object> map)throws Exception;
}
