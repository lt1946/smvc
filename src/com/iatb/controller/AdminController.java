package com.iatb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iatb.pojo.Admin;
import com.iatb.services.AdminService;
import com.iatb.util.eportal.MD5;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/test.do",method=RequestMethod.GET)
	public String test(){
		
		return "admin/test/jstl";
	}
	@RequestMapping(value = "/index.do",method=RequestMethod.GET)
	public String index(){
		return "admin/login";
	}
	
	@RequestMapping(value = "/login.do")
	public String login(HttpSession session,HttpServletRequest req,Model model){
		if(session.getAttribute("admin")==null){
			String loginPwd=req.getParameter("loginPwd");
			String loginName=req.getParameter("loginName");
			String rand=req.getParameter("rand");
			if(session.getAttribute("rand")==null||!rand.equalsIgnoreCase((String)session.getAttribute("rand"))){
				model.addAttribute("error", "error_rand");
				return "admin/login";
			}else{
				Admin tempAdmin = adminService.adminLogin(loginName, MD5.MD5Encode(loginPwd));
				if(tempAdmin!=null){
					session.setAttribute("admin",tempAdmin);
					return "admin/index";
				}else{
					model.addAttribute("error", "error_login");
					return "admin/login";
				}
			}
		}else{
			return "admin/index";
		}
	}
	
}
