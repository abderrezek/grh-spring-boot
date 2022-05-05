package com.grh.grhapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class RootController {

	@GetMapping
	public String index() {
		return "home";
	}
	
}
