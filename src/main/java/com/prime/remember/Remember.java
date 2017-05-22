package com.prime.remember;

public class Remember {

	private int no;
	private String words;
	private int user_no;
	private String grade;
	private String textbook;
	private int lesson;

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTextbook() {
		return textbook;
	}

	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}

	public int getLesson() {
		return lesson;
	}

	public void setLesson(int lesson) {
		this.lesson = lesson;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "\nRemember [no=" + no + ", words=" + words + ", user_no=" + user_no + ", grade=" + grade + ", textbook="
				+ textbook + ", lesson=" + lesson + "]";
	}

}
