package com.iatb.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.iatb.services.GbApiService;
import com.iatb.services.GbGoodsService;
import com.iatb.services.GbSiteService;

@Service
public class GBJob {
	@Autowired
	GbSiteService gbSiteService;
	@Autowired
	GbApiService gbApiService;
	@Autowired
	GbGoodsService gbGoodsService;
	/** 
	 * ���ó��˽����Ѿ�ץȡ��״̬��δץȡ0.  
	 * ץȡ����gbsiteApi
	 */
	@Scheduled(cron="30 10 22 * * ?")
	public void spiderAllApiFromGbSite(){
		gbSiteService.resetStatusButToday();
		gbApiService.spiderAllApiFromGbSite();
	}
	//TODO  ͳ�ƹ���������Ʒ���������Ʒ������վ�������Ļ�ȡ��ǩ�������۸�����ĸ������ȼ�����smvc
}
