package org.microservice.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExample {

	@RequestMapping("/")
    public String index() {
		
        return "Hello world!";
    }
}
