package com.prime.admin;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.prime.study.StudyService;
import com.prime.user.User;
import com.prime.user.UserService;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Inject
	private UserService userService;
	@Inject
	private StudyService studyService;
	@Inject
	private AdminService adminService;

	// @RequestMapping(value = "/admin/login.prime", method = RequestMethod.GET)
	// public String loginForm() {
	// return "main/login/LOGIN";
	// }
	//
	// @RequestMapping(value = "/admin/login.prime", method =
	// RequestMethod.POST)
	// public String login(HttpSession session, User user) throws Exception,
	// IOException {
	//
	// try {
	// User resultUser = service.userLogin(user);
	// logger.info(resultUser.toString());
	//
	// if (resultUser == null || resultUser.getUsername() == null) {
	// return "user/loginError/해당 유저가 없습니다.";
	// } else if (!user.getPasswd().equals(resultUser.getPasswd())) {
	// return "user/loginError/비밀번호가 틀렸습니다.";
	// }
	//
	// session.setAttribute("USER", resultUser);
	//// session.setAttribute("session_username", resultUser.getUsername());
	//// session.setAttribute("session_words", "");
	//
	// return "user/loginSuccess/LOGIN SUCCESS";
	// } catch (NullPointerException e) {
	// return "user/loginError/LOGIN ERROR";
	// }
	// }
	//
	// @RequestMapping("/logout.prime")
	// public ModelAndView logout(HttpSession session) {
	//
	// ModelAndView mav = new ModelAndView();
	//// session.removeAttribute("session_known");
	// session.invalidate();
	//
	// mav.addObject("USER", new User());
	// mav.setViewName("redirect:/login.prime");
	//
	// return mav;
	// }

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
//		session.setAttribute("USER", user);
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
