package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-04-02 23:12:11
 */
public class Open163University  implements Serializable {
	
	private	int	id;
	private	String	name;
	private	String	url;
	private	String	des;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|name:"+name+"|url:"+url+"|des:"+des+"|createTime:"+createTime+"|status:"+status;
	}
	public Open163University() {
		super();
	}
	public Open163University(String name,String url,String des)
	{
		this.name = name;
		this.url = url;
		this.des = des;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
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
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getDes(){
		return des;
	}
	public void setDes(String des){
		this.des = des;
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