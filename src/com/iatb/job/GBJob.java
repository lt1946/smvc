package com.iatb.job;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iatb.bean.GBean;
import com.iatb.pojo.GbApi;
import com.iatb.pojo.GbSite;
import com.iatb.services.GbApiService;
import com.iatb.services.GbGoodsService;
import com.iatb.services.GbSiteService;
import com.iatb.util.DateUtil;
import com.iatb.util.http.MyHttp2;

@Service
public class GBJob {
	private static final Logger log=Logger.getLogger(GBJob.class);
	@Autowired
	GbSiteService gbSiteService;
	@Autowired
	GbApiService gbApiService;
	@Autowired
	GbGoodsService gbGoodsService;
	
	@Scheduled(cron="0 10 23 * * ?")
	public void updateGbsite0(){
		gbSiteService.updateTodayAll();
	}
	//TODO later  put class in  android
	@Scheduled(cron="0 13 23 * * ?")
	public void spiderApi(){
		log.info("==========开始获取团购api============");
		log.info("==========更新过期商品开始============");
		gbGoodsService.updateExpired();
		log.info("==========更新过期商品结束============");
		List<GbSite> gbsite=gbSiteService.browseGbSite();
		for (GbSite site : gbsite) {
			int level=site.getLevel();
			int gbsiteid=site.getId();
			if(site.getStatus()==GBean.STATUS_STOP||site.getStatus()>=GBean.STATUS_ALREALDYSIPDER||level==GBean.LEVEL_LOWEST){
				log.info("==========跳过站点："+gbsiteid+"============");
				continue;
			}
			GbApi api=gbApiService.loadBySiteid(gbsiteid);
			if(api==null){
				log.info("gbsiteid:"+gbsiteid+"\turl:"+site.getSiteUrl()+"\t没有api.\t请手动获取api");
				//TODO手动获取api or auto
				gbSiteService.updateStatus(gbsiteid, GBean.API_STATUS_NODEVELOPER);
				continue;
			}else if(api!=null){	//存在api
				if(api.getStatus()==GBean.STATUS_NOADDAPI){
					log.info("gbsiteid:"+gbsiteid+"\turl:"+site.getSiteUrl()+"\tapi抓取规则未开发。");
					continue;
				}
				int gbapid=api.getId();
				String url=api.getUrl();
				log.info("==========正在获取站点："+gbsiteid+"的ApiURL："+url);
				int apistatus=api.getStatus();
				int apihealth=api.getHealth();
				int apigoods=api.getGoods();
				if(apistatus==GBean.STATUS_NORMAL){
					boolean isConnection=MyHttp2.existsUrl(url);		
					/** check connection，更新goods and health **/
					if(isConnection){
						if(apihealth<0)gbApiService.update("health",GBean.API_HEALTH_NORMAL,gbsiteid);
						/**add Goods**/
						int goodscount=gbApiService.spider(api);
						if(goodscount==1){
							apigoods+=GBean.API_GOODS_OK;
						}else{
							apigoods+=GBean.API_GOODS_NO;
						}
						gbApiService.update("goods", new Object[]{apigoods}, gbapid);
					}else{
						log.info("gbapi:"+gbapid+"\turl:"+url+"\t不能访问！");
						gbApiService.update("health",apihealth-1,gbapid);
					}
					/**连续10天不能打开的api，更新status **/
					if(apihealth<=-10){	
						if(level==10){
							//TODO send to email
						}else{
							gbApiService.update("status",GBean.STATUS_STOP,gbapid);
						}
					}
					/**连续5天没新商品,更新level**/
					if(apigoods<=-5){
						gbSiteService.update("level",level-1,gbsiteid);
					}else if(apigoods>=5){
						gbSiteService.update("level",level+1,gbsiteid);
					}
					gbSiteService.updateStatus(gbsiteid, GBean.API_STATUS_ALREADLYSPIDER);
				}
				gbApiService.update("updateTime", DateUtil.getPlusTimeDate(), gbapid);
			}
			gbSiteService.update("updateTime", DateUtil.getPlusTimeDate(), gbsiteid);
		}
		log.info("==========结束获取团购api============");
	}
}
