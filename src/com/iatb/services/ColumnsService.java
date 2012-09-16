package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Newscolumns;

/** 新闻栏目管理业务逻辑接口 */
public interface ColumnsService {
	/** 浏览新闻栏目 */
	public List<Newscolumns> browseColumns();
	/** 一级新闻栏目列表 */
	public List<Newscolumns> listColumns();	
	/** 下级新闻栏目列表 */
	public List<Newscolumns> listChildColumns(Newscolumns columns);		
	/** 装载指定的新闻栏目 */	
	public Newscolumns loadColumns(Integer id);	
	/** 删除指定的新闻栏目 */	
	public boolean delColumns(Integer id);	
	/** 新增新闻栏目 */	
	public boolean saveColumns(Newscolumns columns);
	/** 修改新闻栏目 */	
	public boolean updateColumns(Newscolumns columns);
}
