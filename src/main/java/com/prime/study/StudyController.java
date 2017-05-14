package com.prime.study;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.prime.remember.RememberService;

@Controller
public class StudyController {

	private static final Logger logger = LoggerFactory.getLogger(StudyController.class);

	@Inject
	private StudyService service;

	@Inject
	private RememberService rememberService;
	
	private String[] middleGrade={"중1","중2","중3"}; 
	private String[] highGrade={"영어I","영어II","영어독해와작문","실용영어I","실용영어II","실용영어독해와작문"}; 

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
	public ModelAndView choice2m() throws Exception{
		// 중등부 교과서 및 저자 목록 출력
		ModelAndView mav=new ModelAndView("study/choice2m/중등 선택");
		Map<String, Object> map=new HashMap<String, Object>();
		
		for(int i=0;i<middleGrade.length;i++){
			map.put("m"+i, service.textbookListByGrade(middleGrade[i]));
		}
//		List<String> textbookList7 = service.textbookListByGrade("중1");
//		List<String> textbookList8= service.textbookListByGrade("중2");
//		List<String> textbookList9 = service.textbookListByGrade("중3");
//		mav.addObject("textbookList7",textbookList7);
//		mav.addObject("textbookList8",textbookList8);
		mav.addObject("textbookList",map);
		return mav;
	}

	@RequestMapping("/Study/high")
	public ModelAndView choice2h() throws Exception { 
		// 고등부 교과서 및 저자 목록 출력 ~ grade 10,11,12
		ModelAndView mav=new ModelAndView("study/choice2h/고등 선택");
		Map<String, Object> map=new HashMap<String, Object>();
		
		for(int i=0;i<highGrade.length;i++){
			map.put("h"+i, service.textbookListByGrade(highGrade[i]));
		}
//		List<String> textbookList10 = service.textbookListByGrade("영어I");
//		List<String> textbookList11= service.textbookListByGrade("영어II");
//		mav.addObject("textbookList10",textbookList10);
//		mav.addObject("textbookList11",textbookList11);

		mav.addObject("textbookList",map);
		
		return mav;
	}

	@RequestMapping("/Study/{grade}/{textbook}")
	public ModelAndView choice3h(@PathVariable String grade, @PathVariable String textbook) throws Exception { 
		// 고등부 교과서 및 저자 선택후 과목 출력
		ModelAndView mav=new ModelAndView("study/choice3/고등 선택-과목");
		Study study = new Study();
		study.setGrade(grade);
		study.setTextbook(textbook);
		int howManyLesson=service.howManyLesson(study);
		mav.addObject("howManyLesson", howManyLesson);
		return mav;
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}")
	public String ready(@ModelAttribute @PathVariable String grade,
			@ModelAttribute @PathVariable String textbook, @PathVariable String lesson, HttpSession session){
//		session.setAttribute("session_textbook", textbook);
		return "study/ready1/체크";
	}
	
	@RequestMapping("/Study/{grade}/{textbook}/{lesson}/start")
	public ModelAndView studyStep1(@ModelAttribute @PathVariable String grade,
			@ModelAttribute @PathVariable String textbook, @PathVariable String lesson, HttpSession session)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		Study study = new Study();
		study.setGrade(grade);
		study.setTextbook(textbook);
		study.setLesson(Integer.parseInt(lesson));

		int howManyLesson = service.howManyLesson(study);
		List<Study> list = service.wordList(study);
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("grade", grade);
		map.put("textbook", textbook);
		map.put("lesson", lesson);
		map.put("username", session.getAttribute("session_username"));
		String known = rememberService.getRememberData(map);
		// 로그인시 얻은 정보로 학습한 데이터를 불러온다. 그 데이터로 리스트를 검사해서 있으면 리스트에서 지운다.
		for(int i=0;i<list.size();i++){
			if(known.contains(list.get(i).getWord())){
				list.remove(i);
			}
		}
		logger.info(list.toString());
		Collections.shuffle(list);
		logger.info(list.toString());

		mav.addObject("howManyLesson", howManyLesson);//왼쪽메뉴 용도
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
