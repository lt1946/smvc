package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 16:16:07
 */
public class GbGoods  implements Serializable {
	
	private	int	id;
	private	int	siteid;
	private	String	url;
	private	String	title;
	private	String	simageurl;
	private	String	mediumimageur;
	private	String	largeimageurl;
	private	int	cityid;
	private	int	status;
	private	String	startdate;
	private	String	enddate;
	private	String	price;
	private	String	value;
	private	int	amount;
	private	String	percent;
	private	String	website;
	private	String	expiration;
	private	String	detail;
	private	String	createTime;
	private	String	updateTime;
	private	int	isExpired;
	private	String	cate;
	private	String	city;
	private	String	siteurl;
	

	@Override
	public String toString() {
		return "|id:"+id+"|siteid:"+siteid+"|url:"+url+"|title:"+title+"|simageurl:"+simageurl+"|mediumimageur:"+mediumimageur+"|largeimageurl:"+largeimageurl+"|cityid:"+cityid+"|status:"+status+"|startdate:"+startdate+"|enddate:"+enddate+"|price:"+price+"|value:"+value+"|amount:"+amount+"|percent:"+percent+"|website:"+website+"|expiration:"+expiration+"|detail:"+detail+"|createTime:"+createTime+"|updateTime:"+updateTime+"|isExpired:"+isExpired+"|cate:"+cate+"|city:"+city+"|siteurl:"+siteurl;
	}
	public GbGoods() {
		super();
	}
	public GbGoods(int siteid,String url,String title,String simageurl,String mediumimageur,String largeimageurl,int cityid,String startdate,String enddate,String price,String value,int amount,String percent,String website,String expiration,String detail,String updateTime,int isExpired,String cate,String city,String siteurl)
	{
		this.siteid = siteid;
		this.url = url;
		this.title = title;
		this.simageurl = simageurl;
		this.mediumimageur = mediumimageur;
		this.largeimageurl = largeimageurl;
		this.cityid = cityid;
		this.status = 0;
		this.startdate = startdate;
		this.enddate = enddate;
		this.price = price;
		this.value = value;
		this.amount = amount;
		this.percent = percent;
		this.website = website;
		this.expiration = expiration;
		this.detail = detail;
		this.createTime = DateUtil.getPlusTimeDate();
		this.updateTime = updateTime;
		this.isExpired = isExpired;
		this.cate = cate;
		this.city = city;
		this.siteurl = siteurl;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getSiteid(){
		return siteid;
	}
	public void setSiteid(int siteid){
		this.siteid = siteid;
	}
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getSimageurl(){
		return simageurl;
	}
	public void setSimageurl(String simageurl){
		this.simageurl = simageurl;
	}
	public String getMediumimageur(){
		return mediumimageur;
	}
	public void setMediumimageur(String mediumimageur){
		this.mediumimageur = mediumimageur;
	}
	public String getLargeimageurl(){
		return largeimageurl;
	}
	public void setLargeimageurl(String largeimageurl){
		this.largeimageurl = largeimageurl;
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
	public String getStartdate(){
		return startdate;
	}
	public void setStartdate(String startdate){
		this.startdate = startdate;
	}
	public String getEnddate(){
		return enddate;
	}
	public void setEnddate(String enddate){
		this.enddate = enddate;
	}
	public String getPrice(){
		return price;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
	public int getAmount(){
		return amount;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}
	public String getPercent(){
		return percent;
	}
	public void setPercent(String percent){
		this.percent = percent;
	}
	public String getWebsite(){
		return website;
	}
	public void setWebsite(String website){
		this.website = website;
	}
	public String getExpiration(){
		return expiration;
	}
	public void setExpiration(String expiration){
		this.expiration = expiration;
	}
	public String getDetail(){
		return detail;
	}
	public void setDetail(String detail){
		this.detail = detail;
	}
	public String getCreateTime(){
		return DateUtil.getPlusTimeDate();
	}
	public void setCreateTime(){
		this.createTime = DateUtil.getPlusTimeDate();
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}
	public int getIsExpired(){
		return isExpired;
	}
	public void setIsExpired(int isExpired){
		this.isExpired = isExpired;
	}
	public String getCate(){
		return cate;
	}
	public void setCate(String cate){
		this.cate = cate;
	}
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getSiteurl(){
		return siteurl;
	}
	public void setSiteurl(String siteurl){
		this.siteurl = siteurl;
	}

}