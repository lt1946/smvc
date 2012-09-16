package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-04-02 23:12:11
 */
public class Open163Openclasstype  implements Serializable {
	
	private	int	openClassId;
	private	int	typeId;
	private	String	createTime;
	private	int	status;
	

	@Override
	public String toString() {
		return "|openClassId:"+openClassId+"|typeId:"+typeId+"|createTime:"+createTime+"|status:"+status;
	}
	public Open163Openclasstype() {
		super();
	}
	public Open163Openclasstype(int openClassId,int typeId)
	{
		this.openClassId = openClassId;
		this.typeId = typeId;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
	}
	public int getOpenClassId(){
		return openClassId;
	}
	public void setOpenClassId(int openClassId){
		this.openClassId = openClassId;
	}
	public int getTypeId(){
		return typeId;
	}
	public void setTypeId(int typeId){
		this.typeId = typeId;
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