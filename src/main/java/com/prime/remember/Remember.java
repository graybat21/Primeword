package com.prime.remember;

public class Remember {

	private String username;
	private String grade;
	private String textbook;
	private int lesson;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "Remember [username=" + username + ", grade=" + grade + ", textbook=" + textbook + ", lesson=" + lesson
				+ "]";
	}

}
