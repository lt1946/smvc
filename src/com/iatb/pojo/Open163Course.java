package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-04-02 23:12:10
 */
public class Open163Course  implements Serializable {
	
	private	int	id;
	private	String	name;
	private	int	openClassId;
	private	int	courseNo;
	private	String	standardSize;
	private	String	standardUrl;
	private	String	standarExt;
	private	String	mobileSize;
	private	String	mobileUrl;
	private	String	mobileExt;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|id:"+id+"|name:"+name+"|openClassId:"+openClassId+"|courseNo:"+courseNo+"|standardSize:"+standardSize+"|standardUrl:"+standardUrl+"|standarExt:"+standarExt+"|mobileSize:"+mobileSize+"|mobileUrl:"+mobileUrl+"|mobileExt:"+mobileExt+"|createTime:"+createTime+"|status:"+status;
	}
	public Open163Course() {
		super();
	}
	public Open163Course(String name,int openClassId,int courseNo,String standardSize,String standardUrl,String standarExt,String mobileSize,String mobileUrl,String mobileExt)
	{
		this.name = name;
		this.openClassId = openClassId;
		this.courseNo = courseNo;
		this.standardSize = standardSize;
		this.standardUrl = standardUrl;
		this.standarExt = standarExt;
		this.mobileSize = mobileSize;
		this.mobileUrl = mobileUrl;
		this.mobileExt = mobileExt;
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
	public int getOpenClassId(){
		return openClassId;
	}
	public void setOpenClassId(int openClassId){
		this.openClassId = openClassId;
	}
	public int getCourseNo(){
		return courseNo;
	}
	public void setCourseNo(int courseNo){
		this.courseNo = courseNo;
	}
	public String getStandardSize(){
		return standardSize;
	}
	public void setStandardSize(String standardSize){
		this.standardSize = standardSize;
	}
	public String getStandardUrl(){
		return standardUrl;
	}
	public void setStandardUrl(String standardUrl){
		this.standardUrl = standardUrl;
	}
	public String getStandarExt(){
		return standarExt;
	}
	public void setStandarExt(String standarExt){
		this.standarExt = standarExt;
	}
	public String getMobileSize(){
		return mobileSize;
	}
	public void setMobileSize(String mobileSize){
		this.mobileSize = mobileSize;
	}
	public String getMobileUrl(){
		return mobileUrl;
	}
	public void setMobileUrl(String mobileUrl){
		this.mobileUrl = mobileUrl;
	}
	public String getMobileExt(){
		return mobileExt;
	}
	public void setMobileExt(String mobileExt){
		this.mobileExt = mobileExt;
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