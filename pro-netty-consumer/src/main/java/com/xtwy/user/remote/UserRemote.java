package com.xtwy.user.remote;

import java.util.List;

import com.xtwy.client.param.Response;
import com.xtwy.user.bean.User;

public interface UserRemote {

	 Response saveUser(User user);
	
     Response saveUsers(List<User> users);
	
}
