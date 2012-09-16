package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-04-02 23:12:11
 */
public class Open163Type  implements Serializable {
	
	private	int	id;
	private	String	name;
	private	String	url;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|name:"+name+"|url:"+url+"|createTime:"+createTime+"|status:"+status;
	}
	public Open163Type() {
		super();
	}
	public Open163Type(String name,String url)
	{
		this.name = name;
		this.url = url;
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