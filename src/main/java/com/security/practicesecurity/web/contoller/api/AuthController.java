package com.security.practicesecurity.web.contoller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@PostMapping("/signup")
	public ResponseEntity<?> signup() {
		
		return ResponseEntity.ok().body(null);
	}
 }
