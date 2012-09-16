package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-01-15 20:29:46
 */
public class Wbcontent  implements Serializable {
	
	private	int	id;
	private	int	wbid;
	private	String	content;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|wbid:"+wbid+"|content:"+content+"|createTime:"+createTime+"|status:"+status;
	}
	public Wbcontent() {
		super();
	}
	public Wbcontent(int wbid,String content)
	{
		this.wbid = wbid;
		this.content = content;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getWbid(){
		return wbid;
	}
	public void setWbid(int wbid){
		this.wbid = wbid;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
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