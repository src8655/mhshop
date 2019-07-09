package com.cafe24.mhshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class MainController {
	
	
	@RequestMapping({"/", "/main"})
	public String main(
			
			) {
		System.out.println("11111111111");
		
		return "main/index";
	}


}
