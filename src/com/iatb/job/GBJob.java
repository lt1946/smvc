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
		log.info("==========��ʼ��ȡ�Ź�api============");
		log.info("==========���¹�����Ʒ��ʼ============");
		gbGoodsService.updateExpired();
		log.info("==========���¹�����Ʒ����============");
		List<GbSite> gbsite=gbSiteService.browseGbSite();
		for (GbSite site : gbsite) {
			int level=site.getLevel();
			int gbsiteid=site.getId();
			if(site.getStatus()==GBean.STATUS_STOP||site.getStatus()>=GBean.STATUS_ALREALDYSIPDER||level==GBean.LEVEL_LOWEST){
				log.info("==========����վ�㣺"+gbsiteid+"============");
				continue;
			}
			GbApi api=gbApiService.loadBySiteid(gbsiteid);
			if(api==null){
				log.info("gbsiteid:"+gbsiteid+"\turl:"+site.getSiteUrl()+"\tû��api.\t���ֶ���ȡapi");
				//TODO�ֶ���ȡapi or auto
				gbSiteService.updateStatus(gbsiteid, GBean.API_STATUS_NODEVELOPER);
				continue;
			}else if(api!=null){	//����api
				if(api.getStatus()==GBean.STATUS_NOADDAPI){
					log.info("gbsiteid:"+gbsiteid+"\turl:"+site.getSiteUrl()+"\tapiץȡ����δ������");
					continue;
				}
				int gbapid=api.getId();
				String url=api.getUrl();
				log.info("==========���ڻ�ȡվ�㣺"+gbsiteid+"��ApiURL��"+url);
				int apistatus=api.getStatus();
				int apihealth=api.getHealth();
				int apigoods=api.getGoods();
				if(apistatus==GBean.STATUS_NORMAL){
					boolean isConnection=MyHttp2.existsUrl(url);		
					/** check connection������goods and health **/
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
						log.info("gbapi:"+gbapid+"\turl:"+url+"\t���ܷ��ʣ�");
						gbApiService.update("health",apihealth-1,gbapid);
					}
					/**����10�첻�ܴ򿪵�api������status **/
					if(apihealth<=-10){	
						if(level==10){
							//TODO send to email
						}else{
							gbApiService.update("status",GBean.STATUS_STOP,gbapid);
						}
					}
					/**����5��û����Ʒ,����level**/
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
		log.info("==========������ȡ�Ź�api============");
	}
}
