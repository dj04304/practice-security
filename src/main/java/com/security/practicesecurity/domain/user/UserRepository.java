package com.security.practicesecurity.domain.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

	public int save(User user) throws Exception;
	public User findUserByUsername(String username) throws Exception;
}
