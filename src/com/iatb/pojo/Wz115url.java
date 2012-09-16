package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-01-23 23:14:59
 */
public class Wz115url  implements Serializable {
	
	private	int	id;
	private	String	title;
	private	String	url;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|title:"+title+"|url:"+url+"|createTime:"+createTime+"|status:"+status;
	}
	public Wz115url() {
		super();
	}
	public Wz115url(String title,String url)
	{
		this.title = title;
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
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
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