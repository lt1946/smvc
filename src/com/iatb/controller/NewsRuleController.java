package com.iatb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iatb.pojo.Newscolumns;
import com.iatb.services.GroupurlroleService;
import com.iatb.services.NewscolumnsService;
import com.iatb.util.Json;

@Controller
@RequestMapping("/rule")
public class NewsRuleController {
	@Autowired
	private GroupurlroleService groupurlroleService;
	@Autowired
	private NewscolumnsService newscolumnsService;
	
	@RequestMapping(value = "/add.do",method=RequestMethod.GET)
	public String add(HttpServletRequest req,Model model){
		List<Newscolumns> newscolumnsList = newscolumnsService.browseNewscolumns();
		req.setAttribute("newscolumnsList", newscolumnsList);
		return "admin/news/addNewsrule";
	}
	@RequestMapping(value = "/view.do",method=RequestMethod.GET)
	public String view(HttpServletRequest req,Model model){
		List<Newscolumns> newscolumnsList = newscolumnsService.browseNewscolumns();
		req.setAttribute("newscolumnsList", newscolumnsList);
		return "admin/news/addNewsrule";
	}
}
