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
	 * ���ý����״̬,δץȡ0��  
	 * ץȡ����gbsiteApi
	 */
	@Scheduled(cron="0 50 * * * ?")
	public void spiderAllApiFromGbSite(){
		gbSiteService.updateTodayAll();
		gbApiService.spiderAllApiFromGbSite();
	}
}
