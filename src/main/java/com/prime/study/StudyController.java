package com.prime.study;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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

	@RequestMapping("/choice1.prime")
	public String choice1() {
		return "study/choice1/초중고 선택";
	}

	@RequestMapping("/choice2e.prime")
	public String choice2e() {
		// 초등부 교과서및 저자 목록 출력
		return "study/choice2e/초등 선택";
	}

	@RequestMapping("/choice2m.prime")
	public String choice2m() {
		// 중등부 교과서 및 저자 목록 출력
		return "study/choice2m/중등 선택";
	}

	@RequestMapping("/choice2h.prime")
	public String choice2h() {
		// 고등부 교과서 및 저자 목록 출력
		return "study/choice2h/고등 선택";
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
		// 고등부 교과서 및 저자 선택후 과목 출력
		return "study/choice3h/고등 선택-과목";
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}")
	public ModelAndView studyStep1(@PathVariable String grade, @PathVariable String textbook,
			@PathVariable String lesson, HttpSession session) throws Exception {
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
//		session.removeAttribute("known");
		mav.addObject("known",session.getAttribute("known"));
		// String title = study.getGrade() + " / " + study.getTextbook() + " / "
		// + study.getLesson();
		mav.setViewName("study/studyStep1/test");
		return mav;
	}
	
//	@RequestMapping(value = "knownWord.prime", method = RequestMethod.POST)
//	@ResponseBody
//	public int saveKnownWord(@RequestBody int no) throws Exception {
//		
//		int isUserExist = service.userExist(username);
//		return isUserExist;
//	}
}
