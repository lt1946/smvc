package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-04-02 23:12:10
 */
public class Open163Lecture  implements Serializable {
	
	private	int	id;
	private	String	name;
	private	int	universityID;
	private	String	des;
	private	String	photoUrl;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|name:"+name+"|universityID:"+universityID+"|des:"+des+"|photoUrl:"+photoUrl+"|createTime:"+createTime+"|status:"+status;
	}
	public Open163Lecture() {
		super();
	}
	public Open163Lecture(String name,int universityID,String des,String photoUrl)
	{
		this.name = name;
		this.universityID = universityID;
		this.des = des;
		this.photoUrl = photoUrl;
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
	public int getUniversityID(){
		return universityID;
	}
	public void setUniversityID(int universityID){
		this.universityID = universityID;
	}
	public String getDes(){
		return des;
	}
	public void setDes(String des){
		this.des = des;
	}
	public String getPhotoUrl(){
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl){
		this.photoUrl = photoUrl;
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