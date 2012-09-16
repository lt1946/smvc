package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 16:16:07
 */
public class GbInvite  implements Serializable {
	
	private	int	id;
	private	String	myuser;
	private	String	iuser;
	private	int	isiteid;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|myuser:"+myuser+"|iuser:"+iuser+"|isiteid:"+isiteid+"|createTime:"+createTime+"|status:"+status;
	}
	public GbInvite() {
		super();
	}
	public GbInvite(String myuser,String iuser,int isiteid)
	{
		this.myuser = myuser;
		this.iuser = iuser;
		this.isiteid = isiteid;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getMyuser(){
		return myuser;
	}
	public void setMyuser(String myuser){
		this.myuser = myuser;
	}
	public String getIuser(){
		return iuser;
	}
	public void setIuser(String iuser){
		this.iuser = iuser;
	}
	public int getIsiteid(){
		return isiteid;
	}
	public void setIsiteid(int isiteid){
		this.isiteid = isiteid;
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