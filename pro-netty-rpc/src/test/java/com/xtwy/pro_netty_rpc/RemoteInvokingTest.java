//package com.xtwy.pro_netty_rpc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ContextConfiguration;
//
//import com.xtwy.netty.annotation.RemoteInvoke;
//import com.xtwy.netty.client.ClientRequest;
//import com.xtwy.netty.client.TcpClient;
//import com.xtwy.netty.util.Response;
//import com.xtwy.user.bean.User;
//import com.xtwy.user.remote.UserRemote;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=RemoteInvokingTest.class)
//@ComponentScan("com.dxfx")
//public class RemoteInvokingTest {
//
//	@RemoteInvoke
//	private UserRemote userRemote;
//	
//	@Test
//	public void testSaveUser() throws InterruptedException {
//		User u = new User();
//		u.setId(1);
//		u.setName("张三");
//		userRemote.saveUser(u);
//	}
//	
//	@Test
//	public void testSaveUsers() throws InterruptedException {
//		List<User> users = new ArrayList<User>();
//		User u = new User();
//		u.setId(1);
//		u.setName("张三");
//		users.add(u);
//		userRemote.saveUsers(users);
//	}
//}
