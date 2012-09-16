package com.iatb.util.service;

import com.iatb.dao.SpiderDaoImpl;
import com.iatb.shs.dao.JobDao;


/**
 * Springπ§≥ß¿‡
 * Class AdsServiceFactory
 */

public class IatbServiceFactory extends ServiceFactory {
	public IatbServiceFactory(String configFileName, String contextName) {
		super(configFileName, contextName);
	}
	
	private final static String contextProp = Constants.PROPERTIE_NAME_FOR_SPRING_CONTEXT;
	private final static String contextName = Constants.PROPERTIE_KEY_FOR_SPRING_CONTEXT;
	
	private static ServiceFactory factory = null;
	
	public IatbServiceFactory() {
		super(contextProp,contextName);
	}
	
	public static synchronized IatbServiceFactory getInstance() {
		if(factory == null){
			factory =  new IatbServiceFactory();
		}
		return (IatbServiceFactory)factory;
	}

	public JobDao getJobDao() {
		return (JobDao) factory.getBean("jobDaoImpl");
	}
}

