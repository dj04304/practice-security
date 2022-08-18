package com.security.practicesecurity.service.auth;

import com.security.practicesecurity.web.dto.auth.UsernameCheckReqDto;

public interface AuthService {

	public boolean checkUsername(UsernameCheckReqDto usernameCheckReqDto) throws Exception;
}
