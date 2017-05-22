package com.prime.admin;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prime.common.PageMaker;
import com.prime.remember.Remember;
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
	public ModelAndView adminUser(PageMaker pagemaker, @RequestParam(value = "o", required = false) String searchOption,
			@RequestParam(value = "k", required = false) String searchKeyword) throws Exception {
		ModelAndView mav = new ModelAndView("admin/userList/유저 관리 페이지");
		HashMap<String, Object> map = new HashMap<String, Object>();

		int page = pagemaker.getPage() != null ? pagemaker.getPage() : 1;
		pagemaker.setPage(page);
		map.put("searchOption", searchOption);
		map.put("searchKeyword", searchKeyword);
		int totalCnt = adminService.userListCnt(map); // DB연동_ 총 갯수 구해오기
		int countPerPage = 5;
		int countPerPaging = 3;

		int first = ((pagemaker.getPage() - 1) * countPerPage) + 1;
		int last = first + countPerPage - 1;

		map.put("first", first);
		map.put("last", last);

		List<User> userList = adminService.userList(map);
		pagemaker.setCount(totalCnt, countPerPage, countPerPaging);
		mav.addObject("userList", userList);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("searchOption", searchOption);
		mav.addObject("searchKeyword", searchKeyword);

		return mav;
	}

	// 관리자의 회원관리 - 회원 강제 탈퇴
	@RequestMapping("/admin/userDelete.prime")
	public ModelAndView userDelete(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
		int no = Integer.parseInt(request.getParameter("no"));
		adminService.userDelete(no);

		mav.setViewName("redirect:/admin/userManagement.prime");
		return mav;
	}
	
	// 관리자의 회원관리 - 회원 상세보기
	@RequestMapping("/admin/userDetail.prime")
	public ModelAndView userDetail(HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		int no = Integer.parseInt(request.getParameter("no"));
		User user = adminService.userDetail(no);
		List<Remember> remember= adminService.knownWordsByUserNo(no);
		
		mav.addObject(user);
		mav.addObject("remember", remember);
				
		mav.setViewName("admin/userDetail/상세보기");
		
		logger.info(remember.toString());
		return mav;
	}

	// 관리자의 단어관리
	@RequestMapping(value = "/admin/wordsManagement.prime")
	public ModelAndView adminWordsForm(PageMaker pagemaker, @RequestParam(value = "o", required = false) String searchOption,
			@RequestParam(value = "k", required = false) String searchKeyword) throws Exception {
		ModelAndView mav = new ModelAndView("admin/wordsList/단어 관리 페이지");
		HashMap<String, Object> map = new HashMap<String, Object>();

		int page = pagemaker.getPage() != null ? pagemaker.getPage() : 1;
		pagemaker.setPage(page);
		map.put("searchOption", searchOption);
		map.put("searchKeyword", searchKeyword);
		int totalCnt = adminService.wordsListCnt(map); // DB연동_ 총 갯수 구해오기
		int countPerPage = 10;
		int countPerPaging = 5;

		int first = ((pagemaker.getPage() - 1) * countPerPage) + 1;
		int last = first + countPerPage - 1;

		map.put("first", first);
		map.put("last", last);

		List<Study> wordsList = adminService.wordsList(map);
		pagemaker.setCount(totalCnt, countPerPage, countPerPaging);
		mav.addObject("wordsList", wordsList);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("searchOption", searchOption);
		mav.addObject("searchKeyword", searchKeyword);
		mav.addObject("totalCount",totalCnt);
		return mav;
	}

	// 관리자의 회원관리 - 한단어 삭제
	@RequestMapping("/admin/wordDelete.prime")
	public ModelAndView wordDelete(HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		int no = Integer.parseInt(request.getParameter("no"));
		adminService.wordDelete(no);
		
		mav.setViewName("redirect:/admin/userManagement.prime");
		return mav;
		
	}
//	@RequestMapping(value = "/admin/wordsList.prime")
//	public ModelAndView adminWordsListBySearch(@RequestParam("searchKeyword") String searchKeyword,
//			@RequestParam("searchOption") String searchOption) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		if (searchKeyword == null) {
//			mav.setViewName("redirect:/admin/wordsManagement.prime");
//			return mav;
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("searchOption", searchOption);
//		map.put("searchKeyword", searchKeyword);
//		logger.info(map.toString());
//		List<Study> wordsList = adminService.adminWordsListBySearch(map);
//		logger.info(wordsList.toString());
//		mav.addObject("wordsList", wordsList);
//		mav.setViewName("admin/wordsList/단어 관리 페이지");
//		return mav;
//	}

//	@ResponseBody
//	@RequestMapping("/admin/textbookCondition.prime")
//	public List<String> adminConditionTextbook(@RequestBody String grade) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("grade", grade);
//		List<String> textbookList = adminService.textbookList(map);
//
//		// ProductDAO dao = new ProductDAO();
//		// ArrayList<String> list =
//		// dao.getSubList(request.getParameter("product"));
//		// JSONArray js = JSONArray.fromObject(list);
//		// JSONObject obj = new JSONObject();
//		// obj.put("VERION_LIST", js.toString());
//		// response.setContentType(CommonFinalInfo.getJsonContentType());
//		// response.getWriter().print(obj);
//
//		return textbookList;
//	}

	// @ResponseBody
	// @RequestMapping("/admin/lessonCondition.prime")
	// public List<String> adminConditionLesson(@RequestBody String grade,
	// @RequestBody String textbook) throws Exception {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("grade", grade);
	// map.put("textbook", textbook);
	//
	// List<String> lessonList = adminService.textbookList(map);
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
