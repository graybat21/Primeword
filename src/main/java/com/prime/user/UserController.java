package com.prime.user;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Inject
	private UserService service;

	@RequestMapping(value = "/login.prime", method = RequestMethod.GET)
	public String loginForm() {
		return "user/login/LOGIN";
	}

	@RequestMapping(value = "/login.prime", method = RequestMethod.POST)
	public String login(HttpSession session, User user) throws Exception, IOException {

		try {
			User resultUser = service.userLogin(user);
			logger.info(resultUser.toString());

			if (resultUser == null || resultUser.getUsername() == null) {
				return "user/loginError/해당 유저가 없습니다.";
			} else if (!user.getPasswd().equals(resultUser.getPasswd())) {
				return "user/loginError/비밀번호가 틀렸습니다.";
			}

			session.setAttribute("USER", resultUser);
//			session.setAttribute("session_username", resultUser.getUsername());
//			session.setAttribute("session_words", "");
			
			return "user/loginSuccess/LOGIN SUCCESS";
		} catch (NullPointerException e) {
			return "user/loginError/LOGIN ERROR";
		}
	}

	@RequestMapping("/logout.prime")
	public ModelAndView logout(HttpSession session) {

		ModelAndView mav = new ModelAndView();
//		session.removeAttribute("session_known");
		session.invalidate();
		
		mav.addObject("USER", new User());
		mav.setViewName("redirect:/login.prime");

		return mav;
	}

	@RequestMapping(value = "/join.prime", method = RequestMethod.GET)
	public String joinForm(Model model) throws Exception {
		List<String> belongList=service.belongList();
		model.addAttribute("belongList",belongList);
		return "user/joinForm/JOIN";
	}

	@RequestMapping(value = "/join.prime", method = RequestMethod.POST)
	public ModelAndView join(User user, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/");

		logger.info(user.toString());
		service.insert(user);
//		session.setAttribute("USER", user);
//		mav.addObject(user);
		return mav;
	}
	
	@RequestMapping(value="duplicationCheck.prime", method=RequestMethod.POST)
	@ResponseBody
	public int userExist(@RequestBody String username) throws Exception {

		if(username.substring(username.length()-1).equals("=")){
			username = username.substring(0, username.length()-1);
		}
		int isUserExist = service.userExist(username);
		logger.info("\n username : "+username + " , isUserExist : "+isUserExist);
		return isUserExist;
	}
}
