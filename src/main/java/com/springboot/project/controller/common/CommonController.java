package com.springboot.project.controller.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class CommonController {

	@Value("${controller.pageUnit}")
	protected int PAGE_UNIT;
	
	@Value("${controller.pageSize}")
	protected int PAGE_SIZE;
}
