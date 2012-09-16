package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 19:56:40
 */
public class GbApi  implements Serializable {
	
	private	int	id;
	private	String	url;
	private	int	gbsiteid;
	private	int	health;
	private	int	goods;
	private	String	createTime;
	private	int	status;
	private	String	updateTime;
	

	@Override
	public String toString() {
		return "|id:"+id+"|url:"+url+"|gbsiteid:"+gbsiteid+"|health:"+health+"|goods:"+goods+"|createTime:"+createTime+"|status:"+status+"|updateTime:"+updateTime;
	}
	public GbApi() {
		super();
	}
	public GbApi(String url,int gbsiteid,int health,int goods,String updateTime)
	{
		this.url = url;
		this.gbsiteid = gbsiteid;
		this.health = health;
		this.goods = goods;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
		this.updateTime = updateTime;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public int getGbsiteid(){
		return gbsiteid;
	}
	public void setGbsiteid(int gbsiteid){
		this.gbsiteid = gbsiteid;
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public int getGoods(){
		return goods;
	}
	public void setGoods(int goods){
		this.goods = goods;
	}
	public String getCreateTime(){
		return DateUtil.getPlusTimeDate();
	}
	public void setCreateTime(){
		this.createTime = DateUtil.getPlusTimeDate();
	}
	public int getStatus(){
		return status;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

}