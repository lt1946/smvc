package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Content;

/**
 * Content管理业务逻辑接口
 * @author Administrator
 * @since  2012-01-14 21:31:05
 */
public interface ContentService {
	/** 浏览 Content*/
	public List<Content> browseContent();
	/** 装载指定的 Content*/
	public Content loadContent(Integer id);
	/** 删除指定的 Content*/	
	public boolean deleteContent(Integer id);
	/** 新增 Content*/
	public boolean saveContent(Content content);
	/** 修改 Content*/	
	public boolean updateContent(Content content);
}