package com.iatb.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.iatb.services.SpiderConentService;
import com.iatb.services.SpiderService;
import com.iatb.services.SpiderWebBeanService;
import com.iatb.services.Wz115urlService;

@Service
public class SpiderJob {
	
	private static final Logger log=Logger.getLogger(SpiderJob.class);
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private SpiderWebBeanService spiderwbService;
	@Autowired
	private SpiderConentService spiderContentService;
	@Autowired
	private Wz115urlService wz115urlService;
	/**
	 * 抓取普通Webbean
	 */
	@Scheduled(cron="0 40 1/5 * * ?")
	public void spiderWebbean(){
		spiderwbService.spiderNormalWB();
	}
	/**
	 * 抓取普通Content
	 */
	@Scheduled(cron="0 50 1/5 * * ?")
	public void spiderContent(){
		spiderContentService.spiderNormalContent();
	}
	
	/**
	 * 抓取115中 80楼的网赚相关文件
	 */
//	@Scheduled(cron="0 6 * * * ?")
	public void spider115Wz(){
		log.info("=========开始抓取115中 80楼的网赚相关文件=============");
		wz115urlService.spider115Wz();
		log.info("=========结束抓取115中 80楼的网赚相关文件=============");
	}
	/**
	 * 抓取已经抓取过的链接
	 */
//	@Scheduled(cron="0 18 * * * ?")
	public void spiderNews(){
		spiderService.spiderNormal();
	}
	/**
	 * 抓取受限制的站点
	 */
//	@Scheduled(cron="0 20 * * * ?")
	public void spiderLimit(){
		spiderService.spiderLimit();
	}
}
