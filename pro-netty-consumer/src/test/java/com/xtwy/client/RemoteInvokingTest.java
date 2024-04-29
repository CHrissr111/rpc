package com.xtwy.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.xtwy.client.annotation.RemoteInvoke;
import com.xtwy.client.param.Response;
import com.xtwy.user.bean.User;
import com.xtwy.user.remote.UserRemote;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RemoteInvokingTest.class)
@ComponentScan("com.xtwy")
public class RemoteInvokingTest {

	@RemoteInvoke
	private UserRemote userRemote;
	
	@Test
	public void testSaveUser() throws InterruptedException {
		User u = new User();
		u.setId(1);
		u.setName("张三");
		Response response = userRemote.saveUser(u);
		System.out.println(JSONObject.toJSONString(response));
	}
	
	@Test
	public void testSaveUsers() throws InterruptedException {
		List<User> users = new ArrayList<User>();
		User u = new User();
		u.setId(1);
		u.setName("张三");
		users.add(u);
		userRemote.saveUsers(users);
	}
}
