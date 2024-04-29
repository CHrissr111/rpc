//package com.xtwy.pro_netty_rpc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//
//import com.xtwy.netty.client.ClientRequest;
//import com.xtwy.netty.client.TcpClient;
//import com.xtwy.netty.util.Response;
//import com.xtwy.user.bean.User;
//
//public class TestTcp {
//	@Test
//	public void testGetResponse() throws InterruptedException {
//		ClientRequest request = new ClientRequest();
//		request.setContent("测试tcp长连接请求");
//		Response resp = TcpClient.send(request);
//		System.out.println(resp.getResult());
//	}
//	
//	@Test
//	public void testSaveUser() throws InterruptedException {
//		ClientRequest request = new ClientRequest();
//		User u = new User();
//		u.setId(1);
//		u.setName("张三");
//		request.setCommand("com.xtwy.user.controller.UserController.saveUser");
//		request.setContent(u);
//		Response resp = TcpClient.send(request);
//		System.out.println(resp.getResult());
//	}
//	
//	@Test
//	public void testSaveUsers() throws InterruptedException {
//		ClientRequest request = new ClientRequest();
//		List<User> users = new ArrayList<User>();
//		User u = new User();
//		u.setId(1);
//		u.setName("张三");
//		users.add(u);
//		request.setCommand("com.xtwy.user.controller.UserController.saveUsers");
//		request.setContent(users);
//		Response resp = TcpClient.send(request);
//		System.out.println(resp.getResult());
//	}
//}
