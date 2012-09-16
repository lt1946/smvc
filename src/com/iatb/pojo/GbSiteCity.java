package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 16:16:07
 */
public class GbSiteCity  implements Serializable {
	
	private	int	gbsiteid;
	private	int	cityid;
	private	int	status;
	

	@Override
	public String toString() {
		return "|gbsiteid:"+gbsiteid+"|cityid:"+cityid+"|status:"+status;
	}
	public GbSiteCity() {
		super();
	}
	public GbSiteCity(int gbsiteid,int cityid)
	{
		this.gbsiteid = gbsiteid;
		this.cityid = cityid;
		this.status = 0;
	}
	public int getGbsiteid(){
		return gbsiteid;
	}
	public void setGbsiteid(int gbsiteid){
		this.gbsiteid = gbsiteid;
	}
	public int getCityid(){
		return cityid;
	}
	public void setCityid(int cityid){
		this.cityid = cityid;
	}
	public int getStatus(){
		return status;
	}
	public void setStatus(int status){
		this.status = status;
	}

}