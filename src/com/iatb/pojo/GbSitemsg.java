package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 16:16:07
 */
public class GbSitemsg  implements Serializable {
	
	private	int	id;
	private	int	siteid;
	private	String	uptime;
	private	String	type;
	private	String	desc;
	private	String	email;
	private	String	tel;
	private	String	address;
	private	String	siteqq;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|siteid:"+siteid+"|uptime:"+uptime+"|type:"+type+"|desc:"+desc+"|email:"+email+"|tel:"+tel+"|address:"+address+"|siteqq:"+siteqq+"|createTime:"+createTime+"|status:"+status;
	}
	public GbSitemsg() {
		super();
	}
	public GbSitemsg(int siteid,String uptime,String type,String desc,String email,String tel,String address,String siteqq)
	{
		this.siteid = siteid;
		this.uptime = uptime;
		this.type = type;
		this.desc = desc;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.siteqq = siteqq;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
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
	public String getUptime(){
		return uptime;
	}
	public void setUptime(String uptime){
		this.uptime = uptime;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getDesc(){
		return desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getSiteqq(){
		return siteqq;
	}
	public void setSiteqq(String siteqq){
		this.siteqq = siteqq;
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