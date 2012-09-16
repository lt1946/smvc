package com.iatb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.iatb.services.SpiderService;
@Controller
@RequestMapping("/spider")
public class SpiderController {

	@Autowired
	private SpiderService spiderService;
	
	/**
	 * ץȡ������վ
	 */
    @RequestMapping(value = "/limit.do", method = RequestMethod.GET)
    public void spiderLimit() {
    	spiderService.spiderLimit();
	}
}
