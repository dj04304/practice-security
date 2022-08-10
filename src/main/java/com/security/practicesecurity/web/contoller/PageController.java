package com.security.practicesecurity.web.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping({"/", "index"})
	public String loadIndex() {
		return "index";
	}
	
	@GetMapping("/auth/signin")
	public String loadsignin() {
		return "/auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String loadsignup() {
		return "/auth/signup";
	}
	
	
}
