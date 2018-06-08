package org.nearsoft.controller;

import org.common.dto.ExampleUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 * 
 * Controller for all users views
 * 
 * */

@Controller
public class UserController {
	
	@RequestMapping(value={"/newUser"}, method = RequestMethod.GET)
	public ModelAndView newUser(){
		ExampleUserDTO user = new ExampleUserDTO();
		ModelAndView modelAndView = new ModelAndView("user/newUser", "user", user);
		return modelAndView;
	}
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	
}
