package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 16:16:07
 */
public class GbCityUrl  implements Serializable {
	
	private	int	gbsiteid;
	private	String	cityurl;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|gbsiteid:"+gbsiteid+"|cityurl:"+cityurl+"|createTime:"+createTime+"|status:"+status;
	}
	public GbCityUrl() {
		super();
	}
	public GbCityUrl(int gbsiteid,String cityurl)
	{
		this.gbsiteid = gbsiteid;
		this.cityurl = cityurl;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
	}
	public int getGbsiteid(){
		return gbsiteid;
	}
	public void setGbsiteid(int gbsiteid){
		this.gbsiteid = gbsiteid;
	}
	public String getCityurl(){
		return cityurl;
	}
	public void setCityurl(String cityurl){
		this.cityurl = cityurl;
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

}