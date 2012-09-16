package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Openclasstype;

/**
 * Open163Openclasstype管理业务逻辑接口
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163OpenclasstypeService {
	/** 浏览 Open163Openclasstype*/
	public List<Open163Openclasstype> browseOpen163Openclasstype();
	/** 装载指定的 Open163Openclasstype*/
	public Open163Openclasstype loadOpen163Openclasstype(Integer id);
	/** 删除指定的 Open163Openclasstype*/	
	public boolean deleteOpen163Openclasstype(Integer id);
	/** 新增 Open163Openclasstype*/
	public boolean saveOpen163Openclasstype(Open163Openclasstype open163Openclasstype);
	/** 修改 Open163Openclasstype*/	
	public boolean updateOpen163Openclasstype(Open163Openclasstype open163Openclasstype);
	/** 更新Open163Openclasstype:id的状态 */
	public void updateStatus(int id,int status);
}