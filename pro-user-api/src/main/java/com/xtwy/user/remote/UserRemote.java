package com.xtwy.user.remote;

import java.util.List;


import com.xtwy.user.model.User;



public interface UserRemote {

	 Object saveUser(User user);
	
	 Object saveUsers(List<User> users);
	
}
