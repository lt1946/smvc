package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Type;

/**
 * Open163Type管理业务逻辑接口
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163TypeService {
	/** 浏览 Open163Type*/
	public List<Open163Type> browseOpen163Type();
	/** 装载指定的 Open163Type*/
	public Open163Type loadOpen163Type(Integer id);
	/** 删除指定的 Open163Type*/	
	public boolean deleteOpen163Type(Integer id);
	/** 新增 Open163Type*/
	public boolean saveOpen163Type(Open163Type open163Type);
	/** 修改 Open163Type*/	
	public boolean updateOpen163Type(Open163Type open163Type);
	/** 更新Open163Type:id的状态 */
	public void updateStatus(int id,int status);
}