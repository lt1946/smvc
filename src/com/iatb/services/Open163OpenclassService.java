package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Openclass;

/**
 * Open163Openclass管理业务逻辑接口
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163OpenclassService {
	/** 浏览 Open163Openclass*/
	public List<Open163Openclass> browseOpen163Openclass();
	/** 装载指定的 Open163Openclass*/
	public Open163Openclass loadOpen163Openclass(Integer id);
	/** 删除指定的 Open163Openclass*/	
	public boolean deleteOpen163Openclass(Integer id);
	/** 新增 Open163Openclass*/
	public boolean saveOpen163Openclass(Open163Openclass open163Openclass);
	/** 修改 Open163Openclass*/	
	public boolean updateOpen163Openclass(Open163Openclass open163Openclass);
	/** 更新Open163Openclass:id的状态 */
	public void updateStatus(int id,int status);
}