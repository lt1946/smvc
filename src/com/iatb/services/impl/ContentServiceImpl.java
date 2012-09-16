package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.ContentDao;
import com.iatb.pojo.Content;
import com.iatb.services.ContentService;

/**
 * Content管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-01-14 21:31:05
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	ContentDao contentDao;
	/** 新增Content */	
	public boolean saveContent(Content columns){
			return contentDao.save(columns);
	}
	/** 修改Content */	
	public boolean updateContent(Content columns){
		return contentDao.update(columns);
	}

	/** 浏览Content */
	public List<Content> browseContent(){
		return contentDao.listAll();
	}
	
	/** 删除指定的Content */
	public boolean deleteContent(Integer id){
		return contentDao.delete(id);
	}

	/** 装载指定的Content */
	public Content loadContent(Integer id){
		return (Content)contentDao.load(id);
	}

}
