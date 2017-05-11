package com.prime.study;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudyController {

	private static final Logger logger = LoggerFactory.getLogger(StudyController.class);

	@Inject
	private StudyService service;

	@RequestMapping("/Study")
	public String choice1() {
		return "study/choice1/초중고 선택";
	}

	@RequestMapping("/Study/elementary")
	public String choice2e() {
		// 초등부 교과서및 저자 목록 출력
		return "study/choice2e/초등 선택";
	}

	@RequestMapping("/Study/middle")
	public String choice2m() {
		// 중등부 교과서 및 저자 목록 출력
		return "study/choice2m/중등 선택";
	}

	@RequestMapping("/Study/high")
	public ModelAndView choice2h() throws Exception { 
		// 고등부 교과서 및 저자 목록 출력 ~ grade 10,11,12
		ModelAndView mav=new ModelAndView("study/choice2h/고등 선택");
		Study study = new Study();
		study.setGrade(10);
		List<String> textbookList10 = service.textbookListByGrade(study);
		study.setGrade(11);
		List<String> textbookList11= service.textbookListByGrade(study);
		study.setGrade(12);
		List<String> textbookList12 = service.textbookListByGrade(study);
		mav.addObject("textbookList10",textbookList10);
		mav.addObject("textbookList11",textbookList11);
		mav.addObject("textbookList12",textbookList12);
		return mav;
	}

	@RequestMapping("/Study/{grade}/{textbook}")
	public ModelAndView choice3h(@PathVariable String grade, @PathVariable String textbook) throws Exception { 
		// 고등부 교과서 및 저자 선택후 과목 출력
		ModelAndView mav=new ModelAndView("study/choice3/고등 선택-과목");
		Study study = new Study();
		study.setGrade(Integer.parseInt(grade));
		study.setTextbook(textbook);
		int howManyLesson=service.howManyLesson(study);
		mav.addObject("howManyLesson", howManyLesson);
		return mav;
	}
	@RequestMapping("/choice3e.prime")
	public String choice3e() {
		// 초등부 교과서 및 저자 선택후 과목 출력
		return "study/choice3e/초등 선택-과목";
	}

	@RequestMapping("/choice3m.prime")
	public String choice3m() {
		// 중등부 교과서 및 저자 선택후 과목 출력
		return "study/choice3m/중등 선택-과목";
	}

	@RequestMapping("/choice3h.prime")
	public String choice3h() {
		return "";
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}")
	public ModelAndView studyStep1(@ModelAttribute @PathVariable String grade,
			@ModelAttribute @PathVariable String textbook, @PathVariable String lesson, HttpSession session)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		Study study = new Study();
		study.setGrade(Integer.parseInt(grade));
		study.setTextbook(textbook);
		study.setLesson(Integer.parseInt(lesson));

		int howManyLesson = service.howManyLesson(study);
		List<Study> list = service.wordList(study);
		Collections.shuffle(list);
		logger.info(list.toString());
		mav.addObject("howManyLesson", howManyLesson);
		mav.addObject("lesson", lesson);
		mav.addObject("list", list);
		// session.removeAttribute("known");
		mav.addObject("known", session.getAttribute("known"));
		// String title = study.getGrade() + " / " + study.getTextbook() + " / "
		// + study.getLesson();
		mav.setViewName("study/studyStep1/test");
		return mav;
	}

	// @RequestMapping(value = "knownWord.prime", method = RequestMethod.POST)
	// @ResponseBody
	// public int saveKnownWord(@RequestBody int no) throws Exception {
	//
	// int isUserExist = service.userExist(username);
	// return isUserExist;
	// }
}
