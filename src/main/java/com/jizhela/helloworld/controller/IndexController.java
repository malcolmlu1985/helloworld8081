package com.jizhela.helloworld.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jizhela.helloworld.bean.User;


//restcontroller是返回实体，controller是视图路径

@Controller
@RequestMapping ( "/index" ) 
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	//使用指定类初始化日志对象，在日志输出的时候，可以打印出日志信息所在类
	//如：Logger logger = LoggerFactory.getLogger(Actions.class);
	//    logger.debug("日志信息");
	//将会打印出: Actions  : 日志信息
	

	@Value(value = "${malcolm.secret}")
	private String secret;
	
	@RequestMapping
	public String index()
	{
		return "hello world~";
	}
	
	@RequestMapping ( "/get" )
	public Map<String, String> get( @RequestParam String name)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("value", "hello world~");
		map.put("secret", secret);
		return map;
	}
	
	@RequestMapping ( "/find/{id}/{name}" )
	public User get( @PathVariable int id, @PathVariable String name)
	{
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setDate(new Date());
		return user;
	}
	
	//web+freemarker
	@RequestMapping ( "/getFreeMarker/{id}/{name}" )
	public String getFreeMarker( @PathVariable int id, @PathVariable String name, ModelMap model)
	{
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setDate(new Date());
	    model.put("user", user);  
		return "web"; //返回的内容就是templetes下面文件的名称  
	}
	
	@RequestMapping ( "/hello/{id}/{name}" )
	   public String hello( @PathVariable int id, @PathVariable String name,ModelMap model){
		
		  logger.info("这里是IndexController");
		  model.put("title", "Hello World Title");
	      model.put("name", name);
	      model.put("id",id);//gender:性别，1：男；0：女；
	      model.put("date",new Date());//gender:性别，1：男；0：女；
	      
	       List<Map<String,Object>> friends =new ArrayList<Map<String,Object>>();
	       Map<String,Object> friend = new HashMap<String,Object>();
	       friend.put("name", "张三");
	       friend.put("id", 20);
	       friend.put("date", new Date());
	       friends.add(friend);
	       friend = new HashMap<String,Object>();
	       friend.put("name", "李四");
	       friend.put("id", 20);
	       friend.put("date", new Date());
	       friends.add(friend);
	       model.put("friends", friends);
	       return "hello";

	    }
}
