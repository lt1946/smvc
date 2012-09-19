package com.iatb.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iatb.bean.GBean;
import com.iatb.dao.GbApiDao;
import com.iatb.pojo.GbApi;
import com.iatb.pojo.GbGoods;
import com.iatb.pojo.GbSite;
import com.iatb.services.GbApiService;
import com.iatb.services.GbGoodsService;
import com.iatb.services.GbSiteService;
import com.iatb.util.DateUtil;
import com.iatb.util.MyJdom;
import com.iatb.util.http.MyHttp2;

/**
 * GbApi管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbApiService")
public class GbApiServiceImpl implements GbApiService {
	private static final Logger log=Logger.getLogger(GbApiServiceImpl.class);
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbApiDao gbApiDao;
	@Autowired
	GbGoodsService gbGoodsService;
	@Autowired
	GbSiteService gbSiteService;
	/** 新增GbApi */	
	public boolean saveGbApi(GbApi columns){
			return gbApiDao.save(columns);
	}
	/** 修改GbApi */	
	public boolean updateGbApi(GbApi columns){
		return gbApiDao.update(columns);
	}
	/** 浏览GbApi */
	public List<GbApi> browseGbApi(){
		return gbApiDao.listAll();
	}
	/** 删除指定的GbApi */
	public boolean deleteGbApi(Integer id){
		return gbApiDao.delete(id);
	}
	/** 装载指定的GbApi */
	public GbApi loadGbApi(Integer id){
		return (GbApi)gbApiDao.load(id);
	}
	/**
	 * 通过siteid获取gbapi
	 * @param gbsiteid
	 * @return
	 */
	public GbApi GetGbApiFromSiteid(int gbsiteid){
		return gbApiDao.get("select * from gb_api where gbsiteid = ?", GbApi.class,new Object[]{gbsiteid});
	}
	/** 抓取api，并保存 **/
	public int spider(GbApi api) {
			String apiUrl=api.getUrl();
			Element e=MyJdom.getRoot(apiUrl);
			if(e==null)return GBean.API_GOODS_NO;
			return SpiderDeferentAPI(api,e);
	}
	/** 抓取不同网页类型的api */
	private int SpiderDeferentAPI(GbApi api,Element e) {
		int siteid=api.getGbsiteid();
		String apiUrl=api.getUrl();
		if(apiUrl.endsWith("/hao123.php")||apiUrl.endsWith("/baidu.php")||apiUrl.endsWith("/api.php")||apiUrl.endsWith("/quanke.php")){
				return spiderHao123(e,apiUrl,siteid);
		}else if(apiUrl.endsWith("/index.php")){
				return spiderIndex(e,apiUrl,siteid);
		}else  if(apiUrl.endsWith("/sohu.php")){
				return spiderSohu(e,apiUrl,siteid);
		}else {
			  log.info("该url:"+apiUrl+"还在开发中！");		  //TODO Later developer spider other api smvc
			  update("status", new Object[]{GBean.API_STATUS_NODEVELOPER, api.getId()});
			  return GBean.API_GOODS_NO;
	    }
	}
	@SuppressWarnings("unchecked")
	private int spiderSohu(Element e, String apiUrl, int siteid) {
		 List<Element> servlets = e.getChildren("Activity"); 
		  if(servlets==null||servlets.size()==0){
			  log.info("获取url:"+apiUrl+"为空！");
			  return GBean.API_GOODS_NO;
		  }else {
			  for (Element display : servlets) {
				  String loc=display.getChild("Url").getTextTrim();
				  boolean gg=gbGoodsService.existByUrl(siteid,loc);		
				  if(gg) continue;																					//TODO later update 人数 smvc
			      Element e_website,e_siteurl,e_city,e_title,e_image,e_startTime,e_endTime,e_value,e_price,e_rebate,e_bought,ee;
			      String website="",siteurl="",city="",title="",image="",startTime="",endTime="",value="",price="",rebate="",bought="",large_image_url="",small_image_url="";
			      int amount=0;
			      if((e_city=display.getChild("CityName"))!=null)			    	  city=e_city.getTextTrim();
			      if((e_title=display.getChild("Title"))!=null)			    	  title=e_title.getTextTrim();
			      if((e_image=display.getChild("ImageUrl"))!=null)			    	  image=e_image.getTextTrim();
			      if((e_startTime=display.getChild("StartTime"))!=null)			    	  startTime=e_startTime.getTextTrim();
			      if((e_endTime=display.getChild("EndTime"))!=null)			    	  endTime=e_endTime.getTextTrim();
			      if((e_value=display.getChild("Value"))!=null)		    	  value=e_value.getTextTrim();	//实际价格			
			      if((e_price=display.getChild("Price"))!=null)			 	  price=e_price.getTextTrim();		//团购价格
			      //TODO  Later 最低人数添加表字段MinBought
			      if((e_rebate=display.getChild("ReBate"))!=null)		rebate=e_rebate.getTextTrim();		//折扣
			      if((e_bought=display.getChild("bought"))!=null)		 bought=e_bought.getTextTrim();//买
			      if((ee=display.getChild("amount"))!=null){			  amount=Integer.parseInt(ee.getTextTrim());  	//人数
			      }else if((e_bought=display.getChild("current_point"))!=null)	  amount=Integer.parseInt(e_bought.getTextTrim());
			      GbGoods goods=GbGoods.initSohuGbGoods( bought, city, image, loc, price, rebate, siteurl, startTime, endTime, title, value, siteid, amount);
			      gbGoodsService.saveGbGoods(goods);
			  }
			  return GBean.API_GOODS_OK;
		  }
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	private int spiderIndex(Element e, String apiUrl,int siteid) {
		 List<Element> servlets = e.getChildren("team");
		  if(servlets==null||servlets.size()==0){
			  servlets = e.getChild("data").getChild("teams").getChildren("team");
		  }
		  if(servlets!=null){
				  for (Element display : servlets) {
					   String loc=display.getChild("link").getTextTrim();
					   boolean gg=gbGoodsService.existByUrl(siteid,loc);
					   if(gg) continue;																			//TODO later update 人数 smvc
	//				  Element display = url.getChild("data").getChild("display");
				      Element e_website,e_siteurl,e_city,e_title,e_image,e_startTime,e_endTime,e_value,e_price,e_rebate,e_bought,ee;
				      String website="",siteurl="",city="",title="",image="",startTime="",endTime="",value="",price="",rebate="",bought="",large_image_url="",small_image_url="";
				      int amount=0;
	//				      if((e_website=display.getChild("website"))!=null)
	//				       website=e_website.getTextTrim();
				      if((e_siteurl=display.getChild("siteurl"))!=null)    	  siteurl=e_siteurl.getTextTrim();
				      if((e_city=display.getChild("city"))!=null)		    	  city=e_city.getTextTrim();
				      if((e_title=display.getChild("title"))!=null)		    	  title=e_title.getTextTrim();
				      if((e_image=display.getChild("image"))!=null)    	  image=e_image.getTextTrim();
				      else{
				    	  if((e_image=display.getChild("small_image_url"))!=null)   		  small_image_url=e_image.getTextTrim();
				    	  if((e_image=display.getChild("large_image_url"))!=null)    		  large_image_url=e_image.getTextTrim();
				      }
				      if((e_startTime=display.getChild("startTime"))!=null){			    	  startTime=e_startTime.getTextTrim();
			    	  }else if((e_startTime=display.getChild("start_date"))!=null)    		  startTime=e_startTime.getTextTrim();
				      if(startTime.length()>12){
				    	  startTime= startTime.split("[+]")[0];
				    	  String date[]=startTime.split("T")[0].split("-");
				    	  String time[]=startTime.split("T")[1].split(":");
				    	  startTime=String.valueOf(new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2])).getTime()).substring(0,10);
				      }
				      if((e_endTime=display.getChild("endTime"))!=null)		    	  endTime=e_endTime.getTextTrim();
				      else  if((e_endTime=display.getChild("end_date"))!=null)    	  endTime=e_endTime.getTextTrim();		//2012-03-12T00:00:00+08:00
				      if(endTime.length()>12){
				    	  endTime= endTime.split("[+]")[0];
				    	  String date[]=endTime.split("T")[0].split("-");
				    	  String time[]=endTime.split("T")[1].split(":");
				    	  endTime=String.valueOf(new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2])).getTime()).substring(0,10);
				      }
				      if((e_value=display.getChild("value"))!=null)				    	  value=e_value.getTextTrim();		//实际价格
				      else  if((e_value=display.getChild("market_price"))!=null)    	  value=e_value.getTextTrim();
				      if((e_price=display.getChild("price"))!=null)					    	  price=e_price.getTextTrim();		//团购价格
				      else if((e_price=display.getChild("team_price"))!=null)	    	  price=e_price.getTextTrim();
				      if((e_rebate=display.getChild("rebate"))!=null)				    	  rebate=e_rebate.getTextTrim();	//折扣
				      if((e_bought=display.getChild("bought"))!=null)			    	  bought=e_bought.getTextTrim();
				      if((ee=display.getChild("amount"))!=null){		    		    	  amount=Integer.parseInt(ee.getTextTrim());  	//人数
				      }else if((e_bought=display.getChild("current_point"))!=null)	  amount=Integer.parseInt(e_bought.getTextTrim());
				      GbGoods goods=GbGoods. initIndexGbGoods( bought, city, image, small_image_url, large_image_url, loc,price, rebate, siteurl,startTime, endTime, title, value,  siteid,  amount);
				      gbGoodsService.saveGbGoods(goods);
				  }
				  return GBean.API_GOODS_OK;
		  }else{
			  log.info("获取url:"+apiUrl+"为空！");
			  return GBean.API_GOODS_NO;
		  }
	}
	@SuppressWarnings("unchecked")
	private int spiderHao123(Element e,String apiUrl, int siteid) {
		 List<Element> servlets = e.getChildren("url");
		  if(servlets==null){
			  log.info("获取url:"+apiUrl+"为空！");
			  return GBean.API_GOODS_NO;
		  }else  for (Element url : servlets) {
		      String loc=url.getChild("loc").getTextTrim();
		      boolean gg=gbGoodsService.existByUrl(siteid,loc);
		      if(gg) continue;																	//TODO later update 人数 smvc
		      Element display = url.getChild("data").getChild("display");
		      Element e_website,e_siteurl,e_city,e_title,e_image,e_startTime,e_endTime,e_value,e_price,e_rebate,e_bought;
		      String website="",siteurl="",city="",title="",image="",startTime="",endTime="",value="",price="",rebate="",bought="";
		      if((e_website=display.getChild("website"))!=null)		  	      website=e_website.getTextTrim();
		      if((e_siteurl=display.getChild("siteurl"))!=null)		    	 	  siteurl=e_siteurl.getTextTrim();
		      if((e_city=display.getChild("city"))!=null)		    	  			  city=e_city.getTextTrim();
		      if((e_title=display.getChild("title"))!=null)		    	  			  title=e_title.getTextTrim();
		      if((e_image=display.getChild("image"))!=null)		    	 	  image=e_image.getTextTrim();
		      if((e_startTime=display.getChild("startTime"))!=null)    	  startTime=e_startTime.getTextTrim();
		      if((e_endTime=display.getChild("endTime"))!=null)	    	  endTime=e_endTime.getTextTrim();
		      if((e_value=display.getChild("value"))!=null)			    	  value=e_value.getTextTrim();
		      if((e_price=display.getChild("price"))!=null)		 			   	  price=e_price.getTextTrim();
		      if((e_rebate=display.getChild("rebate"))!=null)			    	  rebate=e_rebate.getTextTrim();
		      if((e_bought=display.getChild("bought"))!=null)		    	  bought=e_bought.getTextTrim();
		      GbGoods goods=GbGoods.initHao123GbGoods( bought, city, image, loc, price, rebate, siteurl, startTime, endTime, title, value, website);
		      gbGoodsService.saveGbGoods(goods);
		  }
		  return GBean.API_GOODS_OK;
	}
	/** 更新字段 */
	public void update(String name, Object[] o){
		String ns[]=name.split(",");
		Object[] obj = new Object[ns.length+1];
		StringBuffer sql=new StringBuffer("update gb_api set ");
		for (int j = 0; j < ns.length; j++) {
			if(j!=ns.length-1)	sql.append(ns[j]+"=?,");
			else sql.append(ns[j]+"=? ");
			if (o[j] instanceof Integer) {
				obj[j]= Integer.valueOf(o[j].toString());
			}else if (o[j] instanceof String) {
				obj[j]= o[j].toString();
			}		//TODO add other obj type. smvc
		}
		obj[ns.length]=o[ns.length];
		sql.append(" where id=?");
		gbApiDao.execute(sql.toString(),obj);		
	}
	/**抓取所有gbsiteApi**/
	public void spiderAllApiFromGbSite(){				/** TODO	 later  put class in  android  smvc */
		log.info("==========开始获取团购api============");
		log.info("=== 更新过期商品开始 ======");
		gbGoodsService.updateExpired();
		log.info("=== 更新过期商品结束 ======");
		List<GbSite> gbsite=gbSiteService.browseGbSite();
		for (GbSite site : gbsite) {
			int level=site.getLevel();
			int gbsiteid=site.getId();
			if(site.getStatus()==GBean.STATUS_STOP||site.getStatus()>=GBean.STATUS_ALREALDYSIPDER||level==GBean.LEVEL_LOWEST){
				log.info("=== 跳过站点："+gbsiteid+" =====");
				continue;
			}
			GbApi api=GetGbApiFromSiteid(gbsiteid);
			if(api==null){
				log.info("gbsiteid:"+gbsiteid+"\turl:"+site.getSiteUrl()+"\t没有api.\t请手动获取api");	//TODO	手动获取api or auto	smvc
				gbSiteService.updateStatus(gbsiteid, GBean.API_STATUS_NODEVELOPER);
				continue;
			}else if(api!=null){		//存在api
				if(api.getStatus()==GBean.STATUS_NOADDAPI){
					log.info("gbsiteid:"+gbsiteid+"\turl:"+site.getSiteUrl()+"\tapi抓取规则未开发。");	//TODO	添加获取api or auto	smvc
					continue;
				}
				int gbapid=api.getId();
				String url=api.getUrl();
				log.info("===== 正在获取站点："+gbsiteid+"的ApiURL："+url);
				int apistatus=api.getStatus();
				if(apistatus==GBean.STATUS_NORMAL){
					OptSpiderNormalApi(api,url,gbsiteid,gbapid,level);		/** 操作并抓取启用的api */
				}
				update("updateTime", new Object[]{DateUtil.getPlusTimeDate(), gbapid});
				log.info("=====结束获取站点："+gbsiteid+"的ApiURL："+url);
			}
			gbSiteService.update("updateTime",new Object[]{ DateUtil.getPlusTimeDate(), gbsiteid});
		}
		log.info("==========结束获取团购api============");
	}
	/** 操作并抓取启用的api */
	private void OptSpiderNormalApi(GbApi api,String url,int gbsiteid,int gbapid,int level) {
		int apihealth=api.getHealth();
		int apigoods=api.getGoods();
		boolean isConnection=MyHttp2.existsUrl(url);		
		if(isConnection){															/** check connection，Update goods and health **/
			if(apihealth<0)update("health", new Object[]{GBean.API_HEALTH_NORMAL,gbsiteid});
			apigoods+=	spider(api);											/** Spider and Save Goods**/
			update("goods", new Object[]{apigoods, gbapid});
		}else{
			apigoods+=GBean.API_GOODS_NO;
			apihealth+=GBean.API_HEALTH_NOPEN;
			log.info("gbapi:"+gbapid+"\turl:"+url+"\t不能访问！");
			update("goods,health", new Object[]{apigoods,apihealth, gbapid});
		}
		if(apihealth<=-10){			/**连续10天不能打开的api，更新status **/
			if(level==10){
				//TODO send to email
			}
			update("status",new Object[]{GBean.STATUS_STOP,gbapid});
		}
		if(apigoods<=-5){				/**更新level，连续5天有或没新商品！**/
			level+=-1;	
			gbSiteService.update("level,goods",new Object[]{level,0,gbsiteid});
		} else if(apigoods>=5){	
			level+=1;
			gbSiteService.update("level,goods",new Object[]{level,0,gbsiteid});
		}
		gbSiteService.updateStatus(gbsiteid, GBean.API_STATUS_ALREADLYSPIDER);
	}
}
