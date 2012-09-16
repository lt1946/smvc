package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.NewscolumnsDao;
import com.iatb.pojo.Newscolumns;
import com.iatb.services.NewscolumnsService;

/**
 * Newscolumns管理业务逻辑接口实现
 * @author Administrator
 * @since  2011-12-22 17:17:13
 */
@Service("newscolumnsService")
public class NewscolumnsServiceImpl implements NewscolumnsService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	NewscolumnsDao newscolumnsDao;
	/** 新增Newscolumns */	
	public boolean saveNewscolumns(Newscolumns columns){
			return newscolumnsDao.save(columns);
	}
	/** 修改Newscolumns */	
	public boolean updateNewscolumns(Newscolumns columns){ 
		return newscolumnsDao.update(columns);
	}

	/** 浏览Newscolumns */
	public List<Newscolumns> browseNewscolumns(){
		return newscolumnsDao.listAll();
	}
	/** 一级新闻栏目列表 */
	public List<Newscolumns> listNewscolumns(){
		return  newscolumnsDao.list("select * from newscolumns as a where a.newscolumns is null",new Object[]{});
	}
	
	/** 下级新闻栏目列表 */
	@SuppressWarnings("unchecked")
	public List<Newscolumns> listChildColumns(Newscolumns columns){
		if (columns==null){
			return null;
		}else{
			return newscolumnsDao.list("select * from newscolumns as a where a.newscolumns.id=?",new Object[]{columns.getID()});
		}		
	}
	
	/** 删除指定的Newscolumns */
	public boolean deleteNewscolumns(Integer id){
		return newscolumnsDao.delete(id);
	}

	/** 装载指定的Newscolumns */
	public Newscolumns loadNewscolumns(Integer id){
		return (Newscolumns)newscolumnsDao.load(id);
	}

}
