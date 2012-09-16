package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163University;

/**
 * Open163University管理业务逻辑接口
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163UniversityService {
	/** 浏览 Open163University*/
	public List<Open163University> browseOpen163University();
	/** 装载指定的 Open163University*/
	public Open163University loadOpen163University(Integer id);
	/** 删除指定的 Open163University*/	
	public boolean deleteOpen163University(Integer id);
	/** 新增 Open163University*/
	public boolean saveOpen163University(Open163University open163University);
	/** 修改 Open163University*/	
	public boolean updateOpen163University(Open163University open163University);
	/** 更新Open163University:id的状态 */
	public void updateStatus(int id,int status);
}