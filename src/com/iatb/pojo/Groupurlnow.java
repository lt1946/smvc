package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2011-12-15 23:57:26
 */
public class Groupurlnow  implements Serializable {
	
	private	int	id;
	private	int	daily;
	private	int	pagesize;
	private	int	gurid;
	private	String	createTime;
	private	int	status;
	private	int	isEnd;
	private	int	endurlnum;
	private	int	iswb;
	private	String	title;
	private	String	url;
	

	@Override
	public String toString() {
		return "|id:"+id+"|daily:"+daily+"|pagesize:"+pagesize+"|gurid:"+gurid+"|createTime:"+createTime+"|status:"+status+"|isEnd:"+isEnd+"|endurlnum:"+endurlnum+"|iswb:"+iswb+"|title:"+title+"|url:"+url;
	}
	public Groupurlnow() {
		super();
	}
	public Groupurlnow(int daily,int pagesize,int gurid,int isEnd,int endurlnum,int iswb,String title,String url)
	{
		this.daily = daily;
		this.pagesize = pagesize;
		this.gurid = gurid;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
		this.isEnd = isEnd;
		this.endurlnum = endurlnum;
		this.iswb = iswb;
		this.title = title;
		this.url = url;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getDaily(){
		return daily;
	}
	public void setDaily(int daily){
		this.daily = daily;
	}
	public int getPagesize(){
		return pagesize;
	}
	public void setPagesize(int pagesize){
		this.pagesize = pagesize;
	}
	public int getGurid(){
		return gurid;
	}
	public void setGurid(int gurid){
		this.gurid = gurid;
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
	public int getIsEnd(){
		return isEnd;
	}
	public void setIsEnd(int isEnd){
		this.isEnd = isEnd;
	}
	public int getEndurlnum(){
		return endurlnum;
	}
	public void setEndurlnum(int endurlnum){
		this.endurlnum = endurlnum;
	}
	public int getIswb(){
		return iswb;
	}
	public void setIswb(int iswb){
		this.iswb = iswb;
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

}