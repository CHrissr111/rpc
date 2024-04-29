package com.xtwy.netty.util;

public class ResponseUtil {
	
	public static Response createSuccessResult() {
		return new Response();
	}
	
	public static Response createFailResult(String code,String msg) {
		Response Response = new Response();
		Response.setCode(code);
		Response.setMsg(msg);
		return Response;
	}
	
	public static Response createSuccessResult(Object  content) {
		Response response = new Response();
		response.setResult(content);
		return response;
	}
}
