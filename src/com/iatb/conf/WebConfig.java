package com.iatb.conf;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.opensymphony.xwork2.ActionContext;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
      
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	  String projectname = ServletActionContext.getRequest().getContextPath();
//		ActionContext ctx = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest) ctx
//				.get(ServletActionContext.HTTP_REQUEST);
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
//		String projectname = "SpringMVC";
//	  registry.addResourceHandler("/"+projectname+"/js/**").addResourceLocations("/WEB-INF/jsp/static/js/");
//    registry.addResourceHandler("/"+projectname+"/css/**").addResourceLocations("/WEB-INF/jsp/static/css/");
//    registry.addResourceHandler("/"+projectname+"/imgs/**").addResourceLocations("/WEB-INF/jsp/static/imgs/");
  }

}