package com.iatb.util.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class ServiceFactory{
	private static Map<String, ApplicationContext> contextMap = new HashMap<String, ApplicationContext>();
	
	private String conf;
	
	public ServiceFactory(String configFileName,String contextName) {
		conf = PropertyManager.getInstance().getProValue(configFileName, contextName);
		if (contextMap.get(this.getClass().getName()) == null) {
			String[] xmlPaths = getSpringConfig();
			contextMap.put(this.getClass().getName(),new ClassPathXmlApplicationContext(xmlPaths));
		}
	}
	
	public String[] getSpringConfig() {
		if (conf == null)
			return new String[0];
		return conf.split(",");
	}
	
	public Object getBean(String beanId){
		return contextMap.get(this.getClass().getName()).getBean(beanId);
	}
	
}
