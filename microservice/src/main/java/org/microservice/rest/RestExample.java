package org.microservice.rest;

import org.common.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExample {

	public UserDTO user;
	
	@RequestMapping("/")
    public String index() {
		
        return "Hello world!";
    }
}
