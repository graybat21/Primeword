package com.prime.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prime.study.Study;
import com.prime.user.User;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Inject
	private AdminService adminService;

	// admin 메인 페이지
	@RequestMapping(value = "/admin/main.prime")
	public String adminMain() {
		return "admin/mainAdmin/관리자 메인 페이지";
	}

	// admin 유져관리페이지
	@RequestMapping(value = "/admin/userManagement.prime")
	public ModelAndView adminUser() throws Exception {
		ModelAndView mav = new ModelAndView("admin/userList/유저 관리 페이지");

		List<User> userList = adminService.userList();
		mav.addObject(userList);
		logger.info(userList.toString());
		// session.setAttribute("USER", user);
		return mav;
	}

	// 관리자의 회원관리 - 회원 강제 탈퇴
	@RequestMapping("/admin/userDelete.prime")
	public ModelAndView userDelete(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		adminService.userDelete(username);

		mav.setViewName("redirect:/admin/userManagement.prime");
		return mav;

	}

	// 관리자의 단어관리
	@RequestMapping(value = "/admin/wordsManagement.prime")
	public String adminWordsForm() throws Exception {
//		ModelAndView mav = new ModelAndView();
//		List<String> gradeList = adminService.gradeList();
//		logger.info(gradeList.toString());
//		mav.addObject("gradeList", gradeList);
		return "admin/wordsList/단어 관리 페이지";
	}

	@RequestMapping(value = "/admin/wordsList.prime")
	public ModelAndView adminWordsListBySearch(@RequestParam("searchKeyword") String searchKeyword,
			@RequestParam("searchOption") String searchOption) throws Exception {
		ModelAndView mav=new ModelAndView();
		if(searchKeyword == null){
			mav.setViewName("redirect:/admin/wordsManagement.prime");
			return mav;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("searchKeyword", searchKeyword);
		logger.info(map.toString());
		List<Study> wordsList = adminService.adminWordsListBySearch(map);
		logger.info(wordsList.toString());
		mav.addObject("wordsList",wordsList);
		mav.setViewName("admin/wordsList/단어 관리 페이지");
		return mav;
	}
	// // 검색을 통해 단어리스트 뽑기
	// @RequestMapping(value="/admin/wordsManagement.prime",
	// method=RequestMethod.POST)
	// public ModelAndView adminWords(Study study) throws Exception {
	// ModelAndView mav = new ModelAndView("admin/wordsList/단어 관리 페이지");
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("grade", study.getGrade());
	// map.put("textbook", study.getTextbook());
	// map.put("lesson", study.getLesson());
	//// int first = 1;
	//// int last = 10;
	//// map.put("first", first);
	//// map.put("last", last);
	//// List<String> gradeList = adminService.gradeList();
	// List<Study> wordsList = adminService.wordsList(map);
	// mav.addObject("wordsList",wordsList);
	//// mav.addObject("gradeList",gradeList);
	// logger.info(wordsList.toString());
	//
	// return mav;
	// }

	// @ResponseBody
	// @RequestMapping("/admin/textbookCondition.prime")
	// public List<String> adminConditionTextbook(@RequestBody String
	// grade)throws Exception{
	// Map<String, Object> map=new HashMap<String, Object>();
	// map.put("grade", grade);
	// List<String> textbookList=adminService.textbookList(map);
	//
	// ProductDAO dao = new ProductDAO();
	// ArrayList<String> list = dao.getSubList(request.getParameter("product"));
	// JSONArray js = JSONArray.fromObject(list);
	// JSONObject obj = new JSONObject();
	// obj.put("VERION_LIST", js.toString());
	// response.setContentType(CommonFinalInfo.getJsonContentType());
	// response.getWriter().print(obj);
	//
	//
	//
	//
	//
	//
	// return textbookList;
	// }
	// @ResponseBody
	// @RequestMapping("/admin/lessonCondition.prime")
	// public List<String> adminConditionLesson(@RequestBody String grade,
	// @RequestBody String textbook)throws Exception{
	// Map<String, Object> map=new HashMap<String, Object>();
	// map.put("grade", grade);
	// map.put("textbook", textbook);
	//
	// List<String> lessonList=adminService.textbookList(map);
	// return lessonList;
	// }
	// @RequestMapping(value="/sample/selectBoardList.do")
	// public ModelAndView selectBoardList(CommandMap commandMap) throws
	// Exception{
	//
	// List<Map<String,Object>> list =
	// sampleService.selectBoardList(commandMap.getMap());
	// mv.addObject("list", list);
	// if(list.size() > 0){
	// mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
	// }
	// else{
	// mv.addObject("TOTAL", 0);
	// }
	//
	// return mv;
	// }

	// admin 단어관리페이지
	// @RequestMapping(value = "/admin/words.prime")
	// public ModelAndView adminStudy(Study study, HttpSession session) throws
	// Exception {
	// ModelAndView mav = new ModelAndView("redirect:/login.prime");
	//
	// logger.info(user.toString());
	// service.insert(user);
	// session.setAttribute("USER", user);
	// mav.addObject(user);
	// return mav;
	// }

	// @RequestMapping(value="duplicationCheck.prime",
	// method=RequestMethod.POST)
	// @ResponseBody
	// public int userExist(@RequestBody String username) throws Exception {
	//
	// logger.info(username.toString());
	// int isUserExist = service.userExist(username);
	// return isUserExist;
	// }
}
