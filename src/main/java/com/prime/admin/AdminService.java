package com.prime.admin;

import java.util.HashMap;
import java.util.List;

import com.prime.remember.Remember;
import com.prime.study.Study;
import com.prime.user.User;

public interface AdminService {

	public List<User> userList(HashMap<String, Object> map)throws Exception;
	public int userListCnt(HashMap<String, Object> map)throws Exception;
	public void userDelete(int no) throws Exception;
	public User userDetail(int no) throws Exception;
	
	public List<Study> wordsList(HashMap<String, Object> map) throws Exception;
	public int wordsListCnt(HashMap<String, Object> map)throws Exception;
	public void wordDelete(int no) throws Exception;
	public void wordInsert(Study study)throws Exception;
	public List<Remember> knownWordsByUserNo(int user_no)throws Exception;
	
	public int wordsGroupListCnt(HashMap<String, Object> map)throws Exception;
	public List<Study> wordsGroupList(HashMap<String, Object> map)throws Exception;
	public void wordsGroupDelete(Study study) throws Exception;
	
//	public List<String> gradeList()throws Exception;
//	public List<String> textbookList(Map<String, Object> map)throws Exception;
//	public List<String> lessonList(Map<String, Object> map)throws Exception;
}
