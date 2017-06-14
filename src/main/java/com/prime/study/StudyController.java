package com.prime.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.ListUtils;
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

	@RequestMapping("/Study/middle")
	public ModelAndView choice2m(HttpSession session) throws Exception {
		// 중등부 교과서 및 저자 목록 출력
		ModelAndView mav = new ModelAndView("study/choice2m/중등 선택");
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("USER");
		if (user == null) {
			mav.setViewName("redirect:/login.prime");
			return mav;
		}
		Integer temp = service.getCreatorForTextbookList(user.getBelong());
		int creator = temp == null ? 0 : temp;
		System.out.println(creator);
		for (int i = 0; i < middleGrade.length; i++) {
			Study study = new Study();
			study.setCreator(creator);
			study.setGrade(middleGrade[i]);
			map.put("m" + i, service.textbookListByGrade(study));
		}
		// List<String> textbookList7 = service.textbookListByGrade("중1");
		mav.addObject("textbookList", map);

		return mav;
	}

	@RequestMapping("/Study/high")
	public ModelAndView choice2h(HttpSession session) throws Exception {
		// 고등부 교과서 및 저자 목록 출력 ~ grade 10,11,12
		ModelAndView mav = new ModelAndView("study/choice2h/고등 선택");
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("USER");
		if (user == null) {
			mav.setViewName("redirect:/login.prime");
			return mav;
		}
		Integer temp = service.getCreatorForTextbookList(user.getBelong());
		int creator = temp == null ? 0 : temp;
		System.out.println(creator);
		for (int i = 0; i < highGrade.length; i++) {
			Study study = new Study();
			study.setCreator(creator);
			study.setGrade(highGrade[i]);
			map.put("h" + i, service.textbookListByGrade(study));
		}

		mav.addObject("textbookList", map);

		return mav;
	}

	@RequestMapping("/Study/{grade}/{textbook}")
	public ModelAndView choice3(@PathVariable String grade, @PathVariable String textbook) throws Exception {
		// 교과서선택후 몇과까지 있는지 확인해서 보내줌
		ModelAndView mav = new ModelAndView("study/choice3/고등 선택-과목");
		Study study = new Study();
		study.setGrade(grade);
		study.setTextbook(textbook);
		int howManyLesson = service.howManyLesson(study);
		mav.addObject("howManyLesson", howManyLesson);
		return mav;
	}

	// 초등부는 grade 는 초등밖에없고, textbook 은 입문, 도약만 있다.
	@RequestMapping("/Study/elementary")
	public ModelAndView choice3e(HttpSession session) throws Exception {
		// 초등부 교과서및 저자 목록 출력
		ModelAndView mav = new ModelAndView("study/choice3e/초등 선택");
		User user = (User) session.getAttribute("USER");
		if (user == null) {
			mav.setViewName("redirect:/login.prime");
			return mav;
		}
		Study study = new Study();
		study.setGrade("초등");
		study.setTextbook("입문");
		int howManyLessonBasic = service.howManyLesson(study);
		study.setTextbook("도약");
		int howManyLessonAdvanced = service.howManyLesson(study);
		mav.addObject("howManyLessonBasic", howManyLessonBasic);
		mav.addObject("howManyLessonAdvanced", howManyLessonAdvanced);
		return mav;
	}

	private List<Study> removeListByKnownWords(String knownWords, Remember remember, List<Study> list) {
		String[] known = knownWords.trim().split(";");
		// 로그인시 얻은 정보로 학습한 데이터를 불러온다. 그 데이터로 리스트를 검사해서 있으면 리스트에서 지운다.
		for (int i = 0; i < known.length; i++) {
			for (int j = 0; j < list.size(); j++) {
				if (known[i].trim().equals(list.get(j).getWord().trim())) {
					list.remove(j);
					j = list.size();
				}
			}
		}
		return list;
	}

	private void updateRemember(Remember remember) throws Exception {
		if (rememberService.isKnownWords(remember)) {
			System.out.println("학습한적 있음 . update");
			rememberService.updateKnownWords(remember);
		} else {
			System.out.println("학습한적 없음 . insert");
			rememberService.insertKnownWords(remember);
		}
	}

	// 리스트 순서를 user_no와 해당 lesson 을 통해서 바꾸기 , 둘을 더한것만큼 밀어내기
	private List<Study> listChange(List<Study> list, int user_no, int lesson) {
		List<Study> listTemp = new ArrayList<Study>();
		int sizeOfList = list.size();
		int userChange = user_no % 10;
		if(userChange + lesson < sizeOfList){
			for (int i = 0; i < lesson; i++) {
				listTemp.addAll(list.subList(userChange + lesson, sizeOfList));
				listTemp.addAll(list.subList(0, userChange + lesson - 1));
			}
		}
		// logger.info(listTemp.toString());
		return listTemp;
	}
	
	@RequestMapping("/Study/{grade}/{textbook}/{lesson}/{step}")
	public ModelAndView studyStep(@PathVariable String grade, @PathVariable String textbook,
			@PathVariable String lesson, @PathVariable String step, @ModelAttribute("knownWords") String knownWords,
			HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Study study = new Study();
		Remember remember = new Remember();
		User user = (User) session.getAttribute("USER");
		if (user == null) {
			mav.setViewName("redirect:/login.prime");
			return mav;
		}
		boolean alreadyFinishedThisLesson = false;
		String lastIndex = step.substring(step.length()-1);
		System.out.println("step : " + step + ", lastIndex : "+lastIndex);
		if(step.substring(0, 5).equals("ready")){
			// 숫자에 따라서 remember 이용하거나 안함.
			// 1,4 이용안함.
			// 2,3,5,6 이용함.(기억한것을 업데이트하거나 추가한다.)
			if(Integer.parseInt(lastIndex) % 3 == 1){
				// remember 이용 안함
			}else{
				// remember 이용함
				remember.setWords(knownWords);
				remember.setUser_no(user.getNo());
				remember.setGrade(grade);
				remember.setTextbook(textbook);
				remember.setLesson(Integer.parseInt(lesson));
				
				updateRemember(remember);
			}
			mav.setViewName("study/ready"+lastIndex+"/다음스텝 안내문");
			
			return mav;
		}
		
		if(step.substring(0,4).equals("step")){
			// 숫자에 따라서 remember 이용 하거나 안함. 
			// 1,3,4,6 이용안함.(기억한것을 리스트에서 제외하지 않는다.)
			// 2,5 이용함.(기억한것을 리스트에서 제외한다.)
			study.setGrade(grade);
			study.setTextbook(textbook);
			study.setLesson(Integer.parseInt(lesson));
			int howManyLesson = service.howManyLesson(study);
			List<Study> list = service.wordList(study);
			mav.addObject("totalCount", list.size());
			
			list = listChange(list, user.getNo(), Integer.parseInt(lesson));
			
			if(Integer.parseInt(lastIndex) % 3 == 2){
				// remember 이용함.
				remember.setUser_no(user.getNo());
				remember.setGrade(grade);
				remember.setTextbook(textbook);
				remember.setLesson(Integer.parseInt(lesson));
				knownWords = rememberService.rememberKnownWords(remember);
				System.out.println("knownWords : " + knownWords);
				if (knownWords == null) {
					// knownWords 받은게 없음
				} else if (knownWords.trim().equals("") || knownWords.trim().equals(";")) {
					// 학습한적있음. 초기화 또는 손만댔음
				} else if (knownWords.trim().equals("===")) {
					// 학습완료
					alreadyFinishedThisLesson = true;
				} else {
					// 첫스텝에서는 학습한 단어 제외하고 보여준다.
					list = removeListByKnownWords(knownWords, remember, list);
					logger.info(list.toString());
					if (list.size() < 1) {
						alreadyFinishedThisLesson = true;
						// "===" 로 초기화
						rememberService.wordsInitialize(remember);
					}
				}
			}else{
				// remember 이용안함.
			}
			mav.addObject("alreadyFinishedThisLesson", alreadyFinishedThisLesson);
			mav.addObject("howManyLesson", howManyLesson);// 왼쪽메뉴 용도
			mav.addObject("lesson", lesson);
			mav.addObject("list", list);
			mav.addObject("knownWords", knownWords);
			mav.addObject("removeTotalCount", list.size());
			
			mav.setViewName("study/studyStep"+lastIndex+"/STEP"+lastIndex);
			
			return mav;
		}
		// ready, step 둘다 아니면
		mav.setViewName("study/studyStepError/스터디스텝 에러");
		
		return mav;
	}
	
}
