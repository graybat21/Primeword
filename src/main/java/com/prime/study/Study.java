package com.prime.study;

public class Study {

	private int no;
	private String word;
	private String meaning;
	private String grade;
	private String textbook;
	private int lesson;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
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
		return "\n Study [no=" + no + ", word=" + word + ", meaning=" + meaning + ", grade=" + grade + ", textbook="
				+ textbook + ", lesson=" + lesson + "]";
	}

}
