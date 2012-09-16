package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Newscolumns;

/**
 * Newscolumns管理业务逻辑接口
 * @author Administrator
 * @since  2011-12-22 16:26:19
 */
public interface NewscolumnsService {
	/** 浏览 Newscolumns*/
	public List<Newscolumns> browseNewscolumns();
	/** 装载指定的 Newscolumns*/
	public Newscolumns loadNewscolumns(Integer id);
	/** 删除指定的 Newscolumns*/	
	public boolean deleteNewscolumns(Integer id);
	/** 新增 Newscolumns*/
	public boolean saveNewscolumns(Newscolumns newscolumns);
	/** 修改 Newscolumns*/	
	public boolean updateNewscolumns(Newscolumns newscolumns);
	
}