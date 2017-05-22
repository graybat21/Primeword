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

import com.prime.remember.Remember;
import com.prime.remember.RememberService;
import com.prime.user.User;

@Controller
public class StudyController {

	private static final Logger logger = LoggerFactory.getLogger(StudyController.class);

	@Inject
	private StudyService service;

	@Inject
	private RememberService rememberService;

	private String[] middleGrade = { "중1", "중2", "중3" }; // m0, m1, m2
	private String[] highGrade = { "영어I", "영어II", "영어독해와작문", "실용영어I", "실용영어II", "실용영어독해와작문" };
	// h0, h1, h2, h3, h4, h5

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
	public ModelAndView choice2m() throws Exception {
		// 중등부 교과서 및 저자 목록 출력
		ModelAndView mav = new ModelAndView("study/choice2m/중등 선택");
		Map<String, Object> map = new HashMap<String, Object>();

		for (int i = 0; i < middleGrade.length; i++) {
			map.put("m" + i, service.textbookListByGrade(middleGrade[i]));
		}
		// List<String> textbookList7 = service.textbookListByGrade("중1");
		mav.addObject("textbookList", map);

		return mav;
	}

	@RequestMapping("/Study/high")
	public ModelAndView choice2h() throws Exception {
		// 고등부 교과서 및 저자 목록 출력 ~ grade 10,11,12
		ModelAndView mav = new ModelAndView("study/choice2h/고등 선택");
		Map<String, Object> map = new HashMap<String, Object>();

		for (int i = 0; i < highGrade.length; i++) {
			map.put("h" + i, service.textbookListByGrade(highGrade[i]));
		}

		mav.addObject("textbookList", map);

		return mav;
	}

	@RequestMapping("/Study/{grade}/{textbook}")
	public ModelAndView choice3h(@PathVariable String grade, @PathVariable String textbook) throws Exception {
		// 고등부 교과서 및 저자 선택후 과목 출력
		ModelAndView mav = new ModelAndView("study/choice3/고등 선택-과목");
		Study study = new Study();
		study.setGrade(grade);
		study.setTextbook(textbook);
		int howManyLesson = service.howManyLesson(study);
		mav.addObject("howManyLesson", howManyLesson);
		return mav;
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}/ready1")
	public String ready1(@ModelAttribute @PathVariable String grade, @ModelAttribute @PathVariable String textbook,
			@PathVariable String lesson, HttpSession session) throws Exception {
		// session.setAttribute("session_textbook", textbook);
		return "study/ready1/step1 안내문";
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}/step1")
	public ModelAndView studyStep1(@ModelAttribute @PathVariable String grade,
			@ModelAttribute @PathVariable String textbook, @PathVariable String lesson, HttpSession session)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		Study study = new Study();
		Remember remember = new Remember();
		boolean alreadyFinishedThisLesson = false;
		
		study.setGrade(grade);
		study.setTextbook(textbook);
		study.setLesson(Integer.parseInt(lesson));
		int howManyLesson = service.howManyLesson(study);
		List<Study> list = service.wordList(study);

		mav.addObject("totalCount", list.size());
		
		remember.setGrade(grade);
		remember.setTextbook(textbook);
		remember.setLesson(Integer.parseInt(lesson));
		User user = (User) session.getAttribute("USER");
		remember.setUser_no(user.getNo());
		String knownWords = rememberService.rememberKnownWords(remember);
		
		if(knownWords == null){
			// 학습한적없음. 첫학습
		} else if(knownWords.trim().equals("") || knownWords.trim().equals(",")){
			// 학습한적있음. 초기화 또는 손만댔음
		} else if(knownWords.trim().equals("===")){
			// 학습완료
			alreadyFinishedThisLesson = true;
		}else{
			String[] known = knownWords.trim().split(",");
			// 로그인시 얻은 정보로 학습한 데이터를 불러온다. 그 데이터로 리스트를 검사해서 있으면 리스트에서 지운다.
			for (int i = 0; i < known.length; i++) {
				for (int j = 0; j < list.size(); j++) {
					if (known[i].trim().equals(list.get(j).getWord().trim())) {
						list.remove(j);
						j = list.size();
					}
				}
			}
			if(list.size() < 1){
				alreadyFinishedThisLesson=true;
				// "===" 로 초기화
				rememberService.wordsInitialize(remember);
			}
		}
		logger.info(list.toString());
		
		Collections.shuffle(list);

		mav.addObject("alreadyFinishedThisLesson", alreadyFinishedThisLesson);
		mav.addObject("howManyLesson", howManyLesson);// 왼쪽메뉴 용도
		mav.addObject("lesson", lesson);
		mav.addObject("list", list);
		mav.addObject("knownWords", knownWords);

		mav.setViewName("study/studyStep1/체크");
		
		return mav;
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}/ready2")
	public String ready2(@ModelAttribute @PathVariable String grade, @ModelAttribute @PathVariable String textbook,
			@ModelAttribute @PathVariable String lesson, @ModelAttribute("knownWords") String knownWords, HttpSession session) throws Exception {
		Remember remember = new Remember();
		User user = (User) session.getAttribute("USER");
		remember.setWords(knownWords);
		remember.setUser_no(user.getNo());
		remember.setGrade(grade);
		remember.setTextbook(textbook);
		remember.setLesson(Integer.parseInt(lesson));

		logger.info(remember.toString());
		
		if (rememberService.isKnownWords(remember)) {
			System.out.println("true . update");
			rememberService.updateKnownWords(remember);
		} else {
			System.out.println("false . insert");
			rememberService.insertKnownWords(remember);
		}
		
		return "study/ready2/step2 안내문";
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}/step2")
	public ModelAndView studyStep2(@ModelAttribute @PathVariable String grade, @ModelAttribute @PathVariable String textbook,
			@PathVariable String lesson, HttpSession session) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		Study study = new Study();
		Remember remember = new Remember();
		boolean alreadyFinishedThisLesson = false;
		
		study.setGrade(grade);
		study.setTextbook(textbook);
		study.setLesson(Integer.parseInt(lesson));
		int howManyLesson = service.howManyLesson(study);
		List<Study> list = service.wordList(study);

		mav.addObject("totalCount", list.size());
		
		remember.setGrade(grade);
		remember.setTextbook(textbook);
		remember.setLesson(Integer.parseInt(lesson));
		User user = (User) session.getAttribute("USER");
		remember.setUser_no(user.getNo());
		String knownWords = rememberService.rememberKnownWords(remember);
		
		if(knownWords == null){
			// 학습한적없음. 첫학습
		} else if(knownWords.trim().equals("") || knownWords.trim().equals(",")){
			// 학습한적있음. 초기화 또는 손만댔음
		} else if(knownWords.trim().equals("===")){
			// 학습완료
			alreadyFinishedThisLesson = true;
		}else{
			String[] known = knownWords.trim().split(",");
			// 로그인시 얻은 정보로 학습한 데이터를 불러온다. 그 데이터로 리스트를 검사해서 있으면 리스트에서 지운다.
			for (int i = 0; i < known.length; i++) {
				for (int j = 0; j < list.size(); j++) {
					if (known[i].trim().equals(list.get(j).getWord().trim())) {
						list.remove(j);
						j = list.size();
					}
				}
			}
			if(list.size() < 1){
				alreadyFinishedThisLesson=true;
				// "===" 로 초기화
				rememberService.wordsInitialize(remember);
			}
		}
		logger.info(list.toString());
		
		Collections.shuffle(list);

		mav.addObject("alreadyFinishedThisLesson", alreadyFinishedThisLesson);
		mav.addObject("howManyLesson", howManyLesson);// 왼쪽메뉴 용도
		mav.addObject("lesson", lesson);
		mav.addObject("list", list);
		mav.addObject("knownWords", knownWords);
		

		mav.setViewName("study/studyStep2/발음");
		return mav;
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}/ready3")
	public String ready3(@ModelAttribute @PathVariable String grade, @ModelAttribute @PathVariable String textbook,
			@ModelAttribute @PathVariable String lesson, @ModelAttribute("knownWords") String knownWords, HttpSession session) throws Exception {
		Remember remember = new Remember();
		User user = (User) session.getAttribute("USER");
		remember.setWords(knownWords);
		remember.setUser_no(user.getNo());
		remember.setGrade(grade);
		remember.setTextbook(textbook);
		remember.setLesson(Integer.parseInt(lesson));

		logger.info(remember.toString());
		
		if (rememberService.isKnownWords(remember)) {
			System.out.println("true . update");
			rememberService.updateKnownWords(remember);
		} else {
			System.out.println("false . insert");
			rememberService.insertKnownWords(remember);
		}
		
		return "study/ready3/step3 안내문";
	}

	@RequestMapping("/Study/{grade}/{textbook}/{lesson}/step3")
	public ModelAndView studyStep3(@ModelAttribute @PathVariable String grade, @ModelAttribute @PathVariable String textbook,
			@PathVariable String lesson, HttpSession session) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		Study study = new Study();
		Remember remember = new Remember();
		boolean alreadyFinishedThisLesson = false;
		
		study.setGrade(grade);
		study.setTextbook(textbook);
		study.setLesson(Integer.parseInt(lesson));
		int howManyLesson = service.howManyLesson(study);
		List<Study> list = service.wordList(study);

		mav.addObject("totalCount", list.size());
		
		remember.setGrade(grade);
		remember.setTextbook(textbook);
		remember.setLesson(Integer.parseInt(lesson));
		User user = (User) session.getAttribute("USER");
		remember.setUser_no(user.getNo());
		String knownWords = rememberService.rememberKnownWords(remember);
		
		if(knownWords == null){
			// 학습한적없음. 첫학습
		} else if(knownWords.trim().equals("") || knownWords.trim().equals(",")){
			// 학습한적있음. 초기화 또는 손만댔음
		} else if(knownWords.trim().equals("===")){
			// 학습완료
			alreadyFinishedThisLesson = true;
		}else{
			String[] known = knownWords.trim().split(",");
			// 로그인시 얻은 정보로 학습한 데이터를 불러온다. 그 데이터로 리스트를 검사해서 있으면 리스트에서 지운다.
			for (int i = 0; i < known.length; i++) {
				for (int j = 0; j < list.size(); j++) {
					if (known[i].trim().equals(list.get(j).getWord().trim())) {
						list.remove(j);
						j = list.size();
					}
				}
			}
			if(list.size() < 1){
				alreadyFinishedThisLesson=true;
				// "===" 로 초기화
				rememberService.wordsInitialize(remember);
			}
		}
		logger.info(list.toString());
		
		Collections.shuffle(list);

		mav.addObject("alreadyFinishedThisLesson", alreadyFinishedThisLesson);
		mav.addObject("howManyLesson", howManyLesson);// 왼쪽메뉴 용도
		mav.addObject("lesson", lesson);
		mav.addObject("list", list);
		mav.addObject("knownWords", knownWords);
		

		mav.setViewName("study/studyStep3/발음");
		return mav;
	}
}
