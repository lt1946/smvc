package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 16:16:07
 */
public class GbUser  implements Serializable {
	
	private	int	id;
	private	String	name;
	private	String	password;
	private	String	tel;
	private	String	city;
	private	String	createTime;
	private	int	status;
	private	int	gbsiteid;
	

	@Override
	public String toString() {
		return "|id:"+id+"|name:"+name+"|password:"+password+"|tel:"+tel+"|city:"+city+"|createTime:"+createTime+"|status:"+status+"|gbsiteid:"+gbsiteid;
	}
	public GbUser() {
		super();
	}
	public GbUser(String name,String password,String tel,String city,int gbsiteid)
	{
		this.name = name;
		this.password = password;
		this.tel = tel;
		this.city = city;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
		this.gbsiteid = gbsiteid;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
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
	public int getGbsiteid(){
		return gbsiteid;
	}
	public void setGbsiteid(int gbsiteid){
		this.gbsiteid = gbsiteid;
	}

}