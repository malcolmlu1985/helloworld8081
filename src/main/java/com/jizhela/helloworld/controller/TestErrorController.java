package com.jizhela.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestErrorController {
	
	@RequestMapping("/testError")
	public String testError() throws Exception {  
	    throw new Exception("发生错误啦啦啦啦");
	}

}
