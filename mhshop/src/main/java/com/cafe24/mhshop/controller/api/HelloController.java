package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "HelloController", description = "헬로 에이피아이")
public class HelloController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ApiOperation(value = "hello, World API", notes = "hello, World를 반환하는 API, Ajax 통신 확인용.")
	@ResponseBody
	public String helloWorld() {
		//////
		return "hello world";
	}
	
	@RequestMapping(value = "/hellos/{names}", method = RequestMethod.POST)
	@ApiOperation(value = "hello, World API", notes = "hello, World를 반환하는 API, Ajax 통신 확인용.")
	public Map<String, String> hello(
			@PathVariable(value = "names") String names
			) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Name", names);
		return map;
	}
}
