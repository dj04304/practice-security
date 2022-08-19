package com.security.practicesecurity.web.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/grant/test")
public class GrantController {
	
	@GetMapping("/user")
	public ResponseEntity<?> loadUser() {
		return ResponseEntity.ok("ROLE_USER 권한");
	}
	
	@GetMapping("/manager")
	public ResponseEntity<?> loadmanager() {
		return ResponseEntity.ok("ROLE_MANAGER 권한");
	}
	
	
	@GetMapping("/admin")
	public ResponseEntity<?> loadadmin() {
		return ResponseEntity.ok("ROLE_ADMIN 권한");
	}
	
	
	
}
