package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 16:16:07
 */
public class GbBuy  implements Serializable {
	
	private	int	id;
	private	int	gbsiteid;
	private	String	url;
	private	String	title;
	private	String	desc;
	private	String	address;
	private	String	tel;
	private	String	bus;
	private	int	number;
	private	String	code;
	private	String	usetime;
	private	String	datetime;
	private	String	ftime;
	private	String	etime;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|gbsiteid:"+gbsiteid+"|url:"+url+"|title:"+title+"|desc:"+desc+"|address:"+address+"|tel:"+tel+"|bus:"+bus+"|number:"+number+"|code:"+code+"|usetime:"+usetime+"|datetime:"+datetime+"|ftime:"+ftime+"|etime:"+etime+"|createTime:"+createTime+"|status:"+status;
	}
	public GbBuy() {
		super();
	}
	public GbBuy(int gbsiteid,String url,String title,String desc,String address,String tel,String bus,int number,String code,String usetime,String datetime,String ftime,String etime)
	{
		this.gbsiteid = gbsiteid;
		this.url = url;
		this.title = title;
		this.desc = desc;
		this.address = address;
		this.tel = tel;
		this.bus = bus;
		this.number = number;
		this.code = code;
		this.usetime = usetime;
		this.datetime = datetime;
		this.ftime = ftime;
		this.etime = etime;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getGbsiteid(){
		return gbsiteid;
	}
	public void setGbsiteid(int gbsiteid){
		this.gbsiteid = gbsiteid;
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
	public String getDesc(){
		return desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getBus(){
		return bus;
	}
	public void setBus(String bus){
		this.bus = bus;
	}
	public int getNumber(){
		return number;
	}
	public void setNumber(int number){
		this.number = number;
	}
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getUsetime(){
		return usetime;
	}
	public void setUsetime(String usetime){
		this.usetime = usetime;
	}
	public String getDatetime(){
		return datetime;
	}
	public void setDatetime(String datetime){
		this.datetime = datetime;
	}
	public String getFtime(){
		return ftime;
	}
	public void setFtime(String ftime){
		this.ftime = ftime;
	}
	public String getEtime(){
		return etime;
	}
	public void setEtime(String etime){
		this.etime = etime;
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