package com.prime.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Inject
	private UserService service;

	@RequestMapping(value = "/login.prime", method = RequestMethod.GET)
	public String loginForm() {
		return "main/login/LOGIN";
	}

	@RequestMapping(value = "/login.prime", method = RequestMethod.POST)
	public String login(HttpSession session, User user) throws Exception {

		try {
			User resultUser = service.userLogin(user);
			logger.info(resultUser.toString());

			if (resultUser == null || resultUser.getUsername() == null) {
				return "user/loginError/해당 유저가 없습니다.";
			} else if (!user.getPasswd().equals(resultUser.getPasswd())) {
				return "user/loginError/비밀번호가 틀렸습니다.";
			}

			session.setAttribute("USER", resultUser);

			return "user/loginSuccess/LOGIN SUCCESS";
		} catch (NullPointerException e) {
			return "user/loginError/LOGIN ERROR";
		}
	}

	@RequestMapping("/logout.prime")
	public ModelAndView logout(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		session.removeAttribute("known");
		session.invalidate();
		
		mav.addObject("USER", new User());
		mav.setViewName("redirect:/login.prime");

		return mav;
	}

	@RequestMapping(value = "/join.prime", method = RequestMethod.GET)
	public String joinForm() {
		return "main/joinForm/JOIN";
	}

	@RequestMapping(value = "/join.prime", method = RequestMethod.POST)
	public ModelAndView join(User user, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/login.prime");

		logger.info(user.toString());
		service.insert(user);
		session.setAttribute("USER", user);
		mav.addObject(user);
		return mav;
	}
}
