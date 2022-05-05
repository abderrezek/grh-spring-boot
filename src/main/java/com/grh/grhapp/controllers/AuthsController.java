package com.grh.grhapp.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthsController {

	@GetMapping(path = "/login")
	public String login(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails == null) {
			return "auths/login";
		} else {
			return "redirect:/";
		}
	}
	
}
