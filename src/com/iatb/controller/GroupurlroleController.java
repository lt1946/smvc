package com.iatb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iatb.pojo.Groupurlrole;
import com.iatb.services.GroupurlroleService;
import com.iatb.util.Json;

@Controller
@RequestMapping("/groupurlrole")
public class GroupurlroleController {
	@Autowired
	private GroupurlroleService groupurlroleService;
	
	@RequestMapping(value = "/view.do",method=RequestMethod.POST)
	public String view(HttpServletRequest req,HttpServletResponse res,Model model) throws IOException{
		try {
			int page=Integer.parseInt( req.getParameter("page"));
			int rows=Integer.parseInt(req.getParameter("rows"));
			List<Groupurlrole> groupurlroleList = groupurlroleService.view(page,rows);
			res.setContentType("text/html; charset=gbk");
			res.getWriter().print("{\"total\":"+groupurlroleService.count()+",\"rows\":"+Json.list2json(groupurlroleList)+"}");
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	@RequestMapping(value = "/copy.do",method=RequestMethod.GET)
	public String copy(HttpServletRequest req,HttpServletResponse res,Model model) throws IOException{
		try {
			String id=  req.getParameter("id");
			boolean b= groupurlroleService.copyById(id);
			res.setContentType("text/html; charset=gbk");
			res.getWriter().print(b?"¸´ÖÆ³É¹¦":"¸´ÖÆÊ§°Ü");
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	@RequestMapping(value = "/delete.do",method=RequestMethod.GET)
	public String delete(HttpServletRequest req,HttpServletResponse res,Model model) throws IOException{
		try {
			String id=  req.getParameter("id");
			boolean b= groupurlroleService.deleteById(id);
			res.setContentType("text/html; charset=gbk");
			res.getWriter().print(b?"É¾³ý³É¹¦":"É¾³ýÊ§°Ü");
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
