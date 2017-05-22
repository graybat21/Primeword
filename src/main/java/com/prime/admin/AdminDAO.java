package com.prime.admin;

import java.util.HashMap;
import java.util.List;

import com.prime.remember.Remember;
import com.prime.study.Study;
import com.prime.user.User;

public interface AdminDAO {
	public List<User> userList(HashMap<String, Object> map);
	public int userListCnt(HashMap<String, Object> map);
	public void userDelete(int no);
	public User userDetail(int no);

	public List<Study> wordsList(HashMap<String, Object> map);
	public int wordsListCnt(HashMap<String, Object> map);
	public void wordDelete(int no);

	public List<Remember> knownWordsByUserNo(int user_no);
	
	
	
	/*아직 미구현 검색기능*/
//	public List<String> gradeList();
//	public List<String> textbookList(Map<String, Object> map);
//	public List<String> lessonList(Map<String, Object> map);
}
