package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2011-12-22 15:25:53
 */
public class Newscolumns  implements Serializable {
	
	private	int	ID;
	private	int	ParentID;
	private	String	ColumnCode;
	private	String	ColumnName;
	

	@Override
	public String toString() {
		return "|ID:"+ID+"|ParentID:"+ParentID+"|ColumnCode:"+ColumnCode+"|ColumnName:"+ColumnName;
	}
	public Newscolumns() {
		super();
	}
	public Newscolumns(int ID,int ParentID,String ColumnCode,String ColumnName)
	{
		this.ID = ID;
		this.ParentID = ParentID;
		this.ColumnCode = ColumnCode;
		this.ColumnName = ColumnName;
	}
	public int getID(){
		return ID;
	}
	public void setID(int ID){
		this.ID = ID;
	}
	public int getParentID(){
		return ParentID;
	}
	public void setParentID(int ParentID){
		this.ParentID = ParentID;
	}
	public String getColumnCode(){
		return ColumnCode;
	}
	public void setColumnCode(String ColumnCode){
		this.ColumnCode = ColumnCode;
	}
	public String getColumnName(){
		return ColumnName;
	}
	public void setColumnName(String ColumnName){
		this.ColumnName = ColumnName;
	}

}