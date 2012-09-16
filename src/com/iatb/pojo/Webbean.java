package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2011-12-15 14:40:30
 */
public class Webbean  implements Serializable {
	
	private	int	id;
	private	int	pid;
	private	int	gurid;
	private	String	title;
	private	String	url;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|gurid:"+gurid+"|title:"+title+"|url:"+url+"|createTime:"+createTime+"|status:"+status;
	}
	public Webbean() {
		super();
	}
	public Webbean(int gurid,String title,String url)
	{
		this.gurid = gurid;
		this.title = title;
		this.url = url;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
	}
	public Webbean(String title,String url)
	{
		this.title = title;
		this.url = url;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
	}
	public Webbean(int gurid,int pid,String title,String url) {
		this.gurid = gurid;
		this.pid = pid;
		this.title = title;
		this.url = url;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
	}
	public Webbean(int gurid,int pid,String title,String url,String createTime,int status) {
		this.gurid = gurid;
		this.pid = pid;
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
	public int getGurid(){
		return gurid;
	}
	public void setGurid(int gurid){
		this.gurid = gurid;
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
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
}