package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-04-02 23:12:10
 */
public class Open163Openclass  implements Serializable {
	
	private	int	id;
	private	String	name;
	private	String	des;
	private	int	collageId;
	private	int	lecturerId;
	private	int	courseNum;
	private	String	openClassurl;
	private	String	downUrl;
	private	String	photoUrl;
	private	boolean	isOver;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|name:"+name+"|des:"+des+"|collageId:"+collageId+"|lecturerId:"+lecturerId+"|courseNum:"+courseNum+"|openClassurl:"+openClassurl+"|downUrl:"+downUrl+"|photoUrl:"+photoUrl+"|isOver:"+isOver+"|createTime:"+createTime+"|status:"+status;
	}
	public Open163Openclass() {
		super();
	}
	public Open163Openclass(String name,String des,int collageId,int lecturerId,int courseNum,String openClassurl,String downUrl,String photoUrl,boolean isOver)
	{
		this.name = name;
		this.des = des;
		this.collageId = collageId;
		this.lecturerId = lecturerId;
		this.courseNum = courseNum;
		this.openClassurl = openClassurl;
		this.downUrl = downUrl;
		this.photoUrl = photoUrl;
		this.isOver = isOver;
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
	public String getDes(){
		return des;
	}
	public void setDes(String des){
		this.des = des;
	}
	public int getCollageId(){
		return collageId;
	}
	public void setCollageId(int collageId){
		this.collageId = collageId;
	}
	public int getLecturerId(){
		return lecturerId;
	}
	public void setLecturerId(int lecturerId){
		this.lecturerId = lecturerId;
	}
	public int getCourseNum(){
		return courseNum;
	}
	public void setCourseNum(int courseNum){
		this.courseNum = courseNum;
	}
	public String getOpenClassurl(){
		return openClassurl;
	}
	public void setOpenClassurl(String openClassurl){
		this.openClassurl = openClassurl;
	}
	public String getDownUrl(){
		return downUrl;
	}
	public void setDownUrl(String downUrl){
		this.downUrl = downUrl;
	}
	public String getPhotoUrl(){
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl){
		this.photoUrl = photoUrl;
	}
	public boolean getIsOver(){
		return isOver;
	}
	public void setIsOver(boolean isOver){
		this.isOver = isOver;
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