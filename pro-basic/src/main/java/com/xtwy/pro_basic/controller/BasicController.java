package com.xtwy.pro_basic.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xtwy.pro_basic.service.BasicService;
@Configuration
@ComponentScan("com.xtwy")
public class BasicController {


		
		public static void main(String[] args) {
			ApplicationContext context = 
			          new AnnotationConfigApplicationContext(BasicController.class);
			BasicService basicService = context.getBean(BasicService.class);
			try {
				basicService.testSaveUser();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
