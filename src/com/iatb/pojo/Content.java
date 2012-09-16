package com.iatb.pojo;
import com.iatb.util.DateUtil;

/**
 * @Author Administrator
 * @Date 2011-12-14 20:33:06
 */
public class Content {
	
	private	int	id;
	private	String	title;
	private	String	url;
	private	String	content;
	private	int	siteid;
	private	int	groupurlid;
	private	int	status;
	private	String	createTime;
	

	@Override
	public String toString() {
		return "|id:"+id+"|title:"+title+"|url:"+url+"|content:"+content+"|siteid:"+siteid+"|groupurlid:"+groupurlid+"|status:"+status+"|createTime:"+createTime;
	}
	public Content() {
		super();
	}
	public Content(int id,String title,String url,String content,int siteid,int groupurlid)
	{
		this.id = id;
		this.title = title;
		this.url = url;
		this.content = content;
		this.siteid = siteid;
		this.groupurlid = groupurlid;
		this.status = 0;
		this.createTime = DateUtil.getPlusTimeDate();
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
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public int getSiteid(){
		return siteid;
	}
	public void setSiteid(int siteid){
		this.siteid = siteid;
	}
	public int getGroupurlid(){
		return groupurlid;
	}
	public void setGroupurlid(int groupurlid){
		this.groupurlid = groupurlid;
	}
	public int getStatus(){
		return status;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public String getCreateTime(){
		return DateUtil.getPlusTimeDate();
	}
	public void setCreateTime(){
		this.createTime = DateUtil.getPlusTimeDate();
	}

}