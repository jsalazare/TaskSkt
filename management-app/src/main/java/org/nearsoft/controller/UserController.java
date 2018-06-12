package org.nearsoft.controller;

import java.util.ArrayList;
import java.util.List;

import org.common.dto.ExampleUserDTO;
import org.nearsoft.service.ProducerserviceImpl;
import org.nearsoft.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 * 
 * Controller for all users views
 * 
 * */

//add general paths

@Controller
public class UserController {
	
	@Autowired
	private ProducerserviceImpl producerService;

	@RequestMapping(value = { "/newUser" }, method = RequestMethod.GET)
	public ModelAndView newUser() {
		ExampleUserDTO user = new ExampleUserDTO();
		ModelAndView modelAndView = new ModelAndView("user/newUser", "user", user);
		return modelAndView;
	}

	@RequestMapping(value = { "/newUser" }, method = RequestMethod.POST)
	public void newUserSave() {
		// Logic for saving element, maybe calling rabbit service here.
	}

	@RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
	public ModelAndView userList() {
		List<ExampleUserDTO> myUsers = new ArrayList<ExampleUserDTO>();
		ExampleUserDTO user = new ExampleUserDTO();
		user.setName("name1");
		user.setAge(23);
		user.setHeigth(1.8);
		user.setWeight(90.34);
		user.setEmail("asdasd@asdasd.com");

		myUsers.add(user);
		myUsers.add(user);
		myUsers.add(user);
		myUsers.add(user);

		ModelAndView modelAndView = new ModelAndView("user/userList", "myUsers", myUsers);
		return modelAndView;
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}


	
	@RequestMapping("/sendMessage")
	public void sendMessage() {
		producerService.produceMessage("example");
		
	}

}
