//package com.prime.remember;
//
//import java.io.IOException;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class RememberController {
//
//	private static final Logger logger = LoggerFactory.getLogger(RememberController.class);
//
//	@Inject
//	private RememberService service;
//
//	@RequestMapping(value = "recordSession.prime", method = RequestMethod.POST)
//	@ResponseBody
//	public int recordSession(@RequestBody HttpSession session) throws Exception {
////		ModelAndView mav = new ModelAndView("redirect:/login.prime"); String words, 
//
//		service.recordSession(session.getAttribute("session_known"));
////		session.setAttribute("USER", user);
////		mav.addObject(user);
//		return mav;
//	}
//	
//	@RequestMapping(value="duplicationCheck.prime", method=RequestMethod.POST)
//	@ResponseBody
//	public int userExist(@RequestBody String username) throws Exception {
//
//		logger.info(username.toString());
//		int isUserExist = service.userExist(username);
//		return isUserExist;
//	}
//}
