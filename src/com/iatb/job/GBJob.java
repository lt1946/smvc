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
	 * 重置除了今天已经抓取的状态：未抓取0.  
	 * 抓取所有gbsiteApi
	 */
	@Scheduled(cron="30 10 22 * * ?")
	public void spiderAllApiFromGbSite(){
		gbSiteService.resetStatusButToday();
		gbApiService.spiderAllApiFromGbSite();
	}
	//TODO  统计购买最多的商品排序，免费商品最多的网站排序，最多的获取标签，各个价格区间的个数，等级排序。smvc
}
