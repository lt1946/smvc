package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 16:16:07
 */
public class GbRegedit  implements Serializable {
	
	private	int	id;
	private	int	siteid;
	private	String	name;
	private	String	password;
	private	String	email;
	private	String	phone;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|siteid:"+siteid+"|name:"+name+"|password:"+password+"|email:"+email+"|phone:"+phone+"|createTime:"+createTime+"|status:"+status;
	}
	public GbRegedit() {
		super();
	}
	public GbRegedit(int siteid,String name,String password,String email,String phone)
	{
		this.siteid = siteid;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
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
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
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