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
import com.iatb.services.GbApiService;
import com.iatb.services.GbGoodsService;
import com.iatb.util.MyJdom;

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
	public GbApi loadBySiteid(int gbsiteid){
		return gbApiDao.get("select * from gb_api where gbsiteid = ?", GbApi.class,new Object[]{gbsiteid});
	}
	/** 抓取api，并保存 **/
	public int spider(GbApi api) {
			String apiUrl=api.getUrl();
			int siteid=api.getGbsiteid();
			Element e=MyJdom.getRoot(apiUrl);
			if(e==null)return 0;
			if(apiUrl.endsWith("/hao123.php")||apiUrl.endsWith("/baidu.php")||apiUrl.endsWith("/api.php")||apiUrl.endsWith("/quanke.php")){
					return spiderHao123(e,apiUrl,siteid);			//TODO later put methods in new Class Like SpiderDeferentAPI
			}else if(apiUrl.endsWith("/index.php")){
					return spiderIndex(e,apiUrl,siteid);
			}else  if(apiUrl.endsWith("/sohu.php")){
					return spiderSohu(e,apiUrl,siteid);
			}else {
				  //TODO developer spider api
				  log.info("该url:"+apiUrl+"还在开发中！");
				  update("status", GBean.API_STATUS_NODEVELOPER, api.getId());
				  return 0;
		  }
	}
	@SuppressWarnings("unchecked")
	private int spiderSohu(Element e, String apiUrl, int siteid) {
		 List<Element> servlets = e.getChildren("Activity"); 
		  if(servlets==null||servlets.size()==0){
			  log.info("获取url:"+apiUrl+"为空！");
			  return 0;
		  }else {
			  for (Element display : servlets) {
				  String loc=display.getChild("Url").getTextTrim();
				   boolean gg=gbGoodsService.existByUrl(siteid,loc);
				   if(gg) continue;
			      Element e_website,e_siteurl,e_city,e_title,e_image,e_startTime,e_endTime,e_value,e_price,e_rebate,e_bought,ee;
			      String website="",siteurl="",city="",title="",image="",startTime="",endTime="",value="",price="",rebate="",bought="",large_image_url=""
			    	  ,small_image_url="";
			      int amount=0;
			      if((e_city=display.getChild("CityName"))!=null)
			    	  city=e_city.getTextTrim();
			      if((e_title=display.getChild("Title"))!=null)
			    	  title=e_title.getTextTrim();
			      if((e_image=display.getChild("ImageUrl"))!=null)
			    	  image=e_image.getTextTrim();
			      if((e_startTime=display.getChild("StartTime"))!=null)
			    	  startTime=e_startTime.getTextTrim();
			      if((e_endTime=display.getChild("EndTime"))!=null)
			    	  endTime=e_endTime.getTextTrim();
			      if((e_value=display.getChild("Value"))!=null)		//实际价格
			    	  value=e_value.getTextTrim();
			    	  
			      if((e_price=display.getChild("Price"))!=null)		//团购价格
			    	  price=e_price.getTextTrim();
			      //TODO  最低人数添加表字段MinBought
			      if((e_rebate=display.getChild("ReBate"))!=null)	//折扣
			    	  rebate=e_rebate.getTextTrim();
			       
			      if((e_bought=display.getChild("bought"))!=null)	//买
			    	  bought=e_bought.getTextTrim();
			      
			      if((ee=display.getChild("amount"))!=null){		//人数
			    	  amount=Integer.parseInt(ee.getTextTrim());  
			      }else if((e_bought=display.getChild("current_point"))!=null)
			    	  amount=Integer.parseInt(e_bought.getTextTrim());
			      //TODO later  add strutsFunction one line
			      GbGoods goods=new GbGoods();
			      goods.setValue(bought);
			      goods.setCity(city);
			      goods.setMediumimageur(image);
			      goods.setUrl(loc);
			      goods.setPrice(price);
			      goods.setPercent(rebate);
			      goods.setSiteurl(siteurl);
			      goods.setStartdate(startTime);
			      goods.setEnddate(endTime);
			      goods.setTitle(title);
			      goods.setValue(value);
			      goods.setPercent(rebate);
			      goods.setSiteid(siteid);
			      goods.setAmount(amount);
			      goods.setCreateTime();
			      gbGoodsService.saveGbGoods(goods);
			  }
			  return 1;
		  }
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	private int spiderIndex(Element e, String apiUrl,int siteid) {
		 List<Element> servlets = e.getChildren("team");//TODO later jdom get rss
		  if(servlets==null||servlets.size()==0){
			  	  servlets = e.getChild("data").getChild("teams").getChildren("team");
		  }
		  if(servlets!=null){
				  for (Element display : servlets) {
					   String loc=display.getChild("link").getTextTrim();
					   boolean gg=gbGoodsService.existByUrl(siteid,loc);
					   if(gg) continue;
	//				      Element display = url.getChild("data").getChild("display");
				      Element e_website,e_siteurl,e_city,e_title,e_image,e_startTime,e_endTime,e_value,e_price,e_rebate,e_bought,ee;
				      String website="",siteurl="",city="",title="",image="",startTime="",endTime="",value="",price="",rebate="",bought="",large_image_url=""
				    	  ,small_image_url="";
				      int amount=0;
	//				      if((e_website=display.getChild("website"))!=null)
	//				       website=e_website.getTextTrim();
				      if((e_siteurl=display.getChild("siteurl"))!=null)
				    	  siteurl=e_siteurl.getTextTrim();
				      if((e_city=display.getChild("city"))!=null)
				    	  city=e_city.getTextTrim();
				      if((e_title=display.getChild("title"))!=null)
				    	  title=e_title.getTextTrim();
				      if((e_image=display.getChild("image"))!=null)
				    	  image=e_image.getTextTrim();
				      else{
				    	  if((e_image=display.getChild("small_image_url"))!=null)
				    		  small_image_url=e_image.getTextTrim();
				    	  if((e_image=display.getChild("large_image_url"))!=null)
				    		  large_image_url=e_image.getTextTrim();
				      }
				      if((e_startTime=display.getChild("startTime"))!=null){
				    	  startTime=e_startTime.getTextTrim();
			    	  }else if((e_startTime=display.getChild("start_date"))!=null)
			    		  startTime=e_startTime.getTextTrim();
				      if(startTime.length()>12){
				    	  startTime= startTime.split("[+]")[0];
				    	  String date[]=startTime.split("T")[0].split("-");
				    	  String time[]=startTime.split("T")[1].split(":");
				    	  startTime=String.valueOf(new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2])).getTime()).substring(0,10);
				      }
				      if((e_endTime=display.getChild("endTime"))!=null)
				    	  endTime=e_endTime.getTextTrim();
				      else  if((e_endTime=display.getChild("end_date"))!=null)
				    	  endTime=e_endTime.getTextTrim();	//2012-03-12T00:00:00+08:00
				      if(endTime.length()>12){
				    	  endTime= endTime.split("[+]")[0];
				    	  String date[]=endTime.split("T")[0].split("-");
				    	  String time[]=endTime.split("T")[1].split(":");
				    	  endTime=String.valueOf(new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2])).getTime()).substring(0,10);
				      }
				      if((e_value=display.getChild("value"))!=null)		//实际价格
				    	  value=e_value.getTextTrim();
				      else  if((e_value=display.getChild("market_price"))!=null)
				    	  value=e_value.getTextTrim();
				    	  
				      if((e_price=display.getChild("price"))!=null)		//团购价格
				    	  price=e_price.getTextTrim();
				      else if((e_price=display.getChild("team_price"))!=null)
				    	  price=e_price.getTextTrim();
				      
				      if((e_rebate=display.getChild("rebate"))!=null)	//折扣
				    	  rebate=e_rebate.getTextTrim();
				       
				      if((e_bought=display.getChild("bought"))!=null)	
				    	  bought=e_bought.getTextTrim();
				      
				      if((ee=display.getChild("amount"))!=null){		//人数
				    	  amount=Integer.parseInt(ee.getTextTrim());  
				      }else if((e_bought=display.getChild("current_point"))!=null)
				    	  amount=Integer.parseInt(e_bought.getTextTrim());
				      GbGoods goods=new GbGoods();
				      goods.setSiteid(siteid);
				      goods.setValue(bought);
				      goods.setCity(city);
				      goods.setSimageurl(small_image_url);
				      goods.setMediumimageur(image);
				      goods.setLargeimageurl(large_image_url);
				      goods.setUrl(loc);
				      goods.setPrice(price);
				      goods.setPercent(rebate);
				      goods.setSiteurl(siteurl);
				      goods.setStartdate(startTime);
				      goods.setEnddate(endTime);
				      goods.setTitle(title);
				      goods.setValue(value);
				      goods.setPercent(rebate);
				      goods.setAmount(amount);
				      goods.setCreateTime();
				      gbGoodsService.saveGbGoods(goods);
				  }
				  return 1;
		  }else{
			  log.info("获取url:"+apiUrl+"为空！");
			  return 0;
		  }
	}
	@SuppressWarnings("unchecked")
	private int spiderHao123(Element e,String apiUrl, int siteid) {
		 List<Element> servlets = e.getChildren("url");
		  if(servlets==null){
			  log.info("获取url:"+apiUrl+"为空！");
			  return 0;
		  }else  for (Element url : servlets) {
		      String loc=url.getChild("loc").getTextTrim();
		      boolean gg=gbGoodsService.existByUrl(siteid,loc);
		      if(gg) continue;
		      Element display = url.getChild("data").getChild("display");
		      Element e_website,e_siteurl,e_city,e_title,e_image,e_startTime,e_endTime,e_value,e_price,e_rebate,e_bought;
		      String website="",siteurl="",city="",title="",image="",startTime="",endTime="",value="",price="",rebate="",bought="";
		      if((e_website=display.getChild("website"))!=null)
		       website=e_website.getTextTrim();
		      if((e_siteurl=display.getChild("siteurl"))!=null)
		    	  siteurl=e_siteurl.getTextTrim();
		      if((e_city=display.getChild("city"))!=null)
		    	  city=e_city.getTextTrim();
		      if((e_title=display.getChild("title"))!=null)
		    	  title=e_title.getTextTrim();
		      if((e_image=display.getChild("image"))!=null)
		    	  image=e_image.getTextTrim();
		      if((e_startTime=display.getChild("startTime"))!=null)
		    	  startTime=e_startTime.getTextTrim();
		      if((e_endTime=display.getChild("endTime"))!=null)
		    	  endTime=e_endTime.getTextTrim();
		      if((e_value=display.getChild("value"))!=null)
		    	  value=e_value.getTextTrim();
		      if((e_price=display.getChild("price"))!=null)
		    	  price=e_price.getTextTrim();
		      if((e_rebate=display.getChild("rebate"))!=null)
		    	  rebate=e_rebate.getTextTrim();
		      if((e_bought=display.getChild("bought"))!=null)
		    	  bought=e_bought.getTextTrim();
		      GbGoods goods=new GbGoods();
		      goods.setValue(bought);
		      goods.setCity(city);
		      goods.setEnddate(endTime);
		      goods.setMediumimageur(image);
		      goods.setUrl(loc);
		      goods.setPrice(price);
		      goods.setPercent(rebate);
		      goods.setSiteurl(siteurl);
		      goods.setStartdate(startTime);
		      goods.setTitle(title);
		      goods.setValue(value);
		      goods.setWebsite(website==null?"":website.length()>10?website.substring(0,10):website);
		      goods.setCreateTime();
		      gbGoodsService.saveGbGoods(goods);
		  }
		  return 1;
	}
	/** 更新字段 */
	public void update(String name, Object i,int id){
		if (i instanceof Integer) {
			gbApiDao.execute("update gb_api set "+name+"=? where id=?", new Object[]{Integer.valueOf(i.toString()),id});
		}else if (i instanceof String) {
			gbApiDao.execute("update gb_api set "+name+"=? where id=?", new Object[]{i.toString(),id});
		}
	}

}
