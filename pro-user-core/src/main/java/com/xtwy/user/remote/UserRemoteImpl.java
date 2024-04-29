package com.xtwy.user.remote;

import java.util.List;

import javax.annotation.Resource;

import com.xtwy.netty.annotation.Remote;
import com.xtwy.netty.util.ResponseUtil;
import com.xtwy.user.model.User;
import com.xtwy.user.service.UserService;

@Remote
public class UserRemoteImpl implements UserRemote{
	@Resource
	private UserService userservice; 
	
	public Object saveUser(User user) {
		userservice.save(user);
		return ResponseUtil.createSuccessResult(user);
	}
	
	public Object saveUsers(List<User> users) {
		userservice.saveList(users);
		return ResponseUtil.createSuccessResult(users);
	}

}
