package com.security.practicesecurity.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.practicesecurity.handler.aop.annotation.Timer;
import com.security.practicesecurity.handler.exception.CustomValidationApiException;
import com.security.practicesecurity.service.auth.AuthService;
import com.security.practicesecurity.service.auth.PrincipalDetailsService;
import com.security.practicesecurity.web.dto.CMRespDto;
import com.security.practicesecurity.web.dto.auth.SignupReqDto;
import com.security.practicesecurity.web.dto.auth.UsernameCheckReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final PrincipalDetailsService principalDetailsService;
	private final AuthService authService;
	
	@Timer
	@GetMapping("/signup/validation/username")
	public ResponseEntity<?> checkUsername(@Valid UsernameCheckReqDto usernameCheckReqDto, BindingResult bindingResult) {
		
		boolean status = false;
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMessage =  new HashMap<String, String>();
			
			bindingResult.getFieldErrors().forEach(error -> {
				errorMessage.put(error.getField(), error.getDefaultMessage());
			});
			
			
		}
		
		try {
			status = authService.checkUsername(usernameCheckReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "서버오류", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입 가능", status));
	}
	

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid SignupReqDto signupReqDto, BindingResult bindingResult) {
		boolean status = false;
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMessage =  new HashMap<String, String>();
					
					bindingResult.getFieldErrors().forEach(error -> {
						errorMessage.put(error.getField(), error.getDefaultMessage());
					});
					
					throw new CustomValidationApiException("유효성 검사 실패", errorMessage);
				}
		
		try {
			status =	principalDetailsService.adduser(signupReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "회원가입 실패" , status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입 성공" , status));
	}
 }
