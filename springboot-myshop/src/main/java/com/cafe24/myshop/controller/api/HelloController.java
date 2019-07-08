package com.cafe24.myshop.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "HelloController", description = "헬로 에이피아이")
public class HelloController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ApiOperation(value = "hello, World API", notes = "hello, World를 반환하는 API, Ajax 통신 확인용.")
	@ResponseBody
	public String helloWorld() {
		return "hello, World";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.POST)
	public String helloWorld(@PathVariable(value = "name") String name) {
		return "Name : " + name;
	}
}
