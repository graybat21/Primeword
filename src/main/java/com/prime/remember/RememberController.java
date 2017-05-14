package com.prime.remember;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RememberController {

	private static final Logger logger = LoggerFactory.getLogger(RememberController.class);

	@Inject
	private RememberService service;

//	@RequestMapping(value = "/remember.prime", method = RequestMethod.POST)
//	public String recordSession(HttpSession session, Remember remember) throws Exception, IOException {
//
//		try {
//			User resultUser = service.userLogin(user);
//			logger.info(resultUser.toString());
//
//			if (resultUser == null || resultUser.getUsername() == null) {
//				return "user/loginError/해당 유저가 없습니다.";
//			} else if (!user.getPasswd().equals(resultUser.getPasswd())) {
//				return "user/loginError/비밀번호가 틀렸습니다.";
//			}
//
//			session.setAttribute("USER", resultUser);
//
//			return "user/loginSuccess/LOGIN SUCCESS";
//		} catch (NullPointerException e) {
//			return "user/loginError/LOGIN ERROR";
//		}
//	}


	@RequestMapping(value = "recordSession.prime", method = RequestMethod.POST)
	@ResponseBody
	public int recordSession(@RequestBody HttpSession session) throws Exception {
//		ModelAndView mav = new ModelAndView("redirect:/login.prime"); String words, 

		logger.info(user.toString());
		service.insert(user);
//		session.setAttribute("USER", user);
//		mav.addObject(user);
		return mav;
	}
	
	@RequestMapping(value="duplicationCheck.prime", method=RequestMethod.POST)
	@ResponseBody
	public int userExist(@RequestBody String username) throws Exception {

		logger.info(username.toString());
		int isUserExist = service.userExist(username);
		return isUserExist;
	}
}
