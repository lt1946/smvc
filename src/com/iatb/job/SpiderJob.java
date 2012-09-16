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
	 * ץȡ��ͨWebbean
	 */
	@Scheduled(cron="0 40 1/5 * * ?")
	public void spiderWebbean(){
		spiderwbService.spiderNormalWB();
	}
	/**
	 * ץȡ��ͨContent
	 */
	@Scheduled(cron="0 50 1/5 * * ?")
	public void spiderContent(){
		spiderContentService.spiderNormalContent();
	}
	
	/**
	 * ץȡ115�� 80¥����׬����ļ�
	 */
//	@Scheduled(cron="0 6 * * * ?")
	public void spider115Wz(){
		log.info("=========��ʼץȡ115�� 80¥����׬����ļ�=============");
		wz115urlService.spider115Wz();
		log.info("=========����ץȡ115�� 80¥����׬����ļ�=============");
	}
	/**
	 * ץȡ�Ѿ�ץȡ��������
	 */
//	@Scheduled(cron="0 18 * * * ?")
	public void spiderNews(){
		spiderService.spiderNormal();
	}
	/**
	 * ץȡ�����Ƶ�վ��
	 */
//	@Scheduled(cron="0 20 * * * ?")
	public void spiderLimit(){
		spiderService.spiderLimit();
	}
}
