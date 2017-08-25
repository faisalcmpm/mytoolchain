package com.ibsplc.ndcapp;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibsplc.ndcapp.test.TestJsonResponse;


@Controller
public class HelloController {
	 
	final Logger l = Logger.getLogger("HelloController.class");
	
	@RequestMapping(value="/hello.htm")
	@ResponseBody
	public String sayHello(){
		
	return "hello"; 
	}
	
	
	@RequestMapping(value="/hellojson.htm")
	@ResponseBody 
	public TestJsonResponse sayHelloJson(){
		l.debug(" Start hellojson");
		TestJsonResponse tj= new TestJsonResponse();
		tj.setAge(32);
		tj.setName("V K");
		l.debug(" end hellojson");

		return tj;
		
	}
	
	

}
