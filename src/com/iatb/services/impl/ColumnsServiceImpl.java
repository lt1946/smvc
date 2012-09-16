package com.iatb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iatb.dao.NewscolumnsDao;
import com.iatb.pojo.Newscolumns;
import com.iatb.services.NewscolumnsService;

/** 新闻栏目管理业务逻辑接口实现 */
@Service("newscolumnsService")
public class ColumnsServiceImpl implements NewscolumnsService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	NewscolumnsDao newscolumnsDao;
	/** 新增新闻栏目 */	
	public boolean saveNewscolumns(Newscolumns columns){
			return newscolumnsDao.save(columns);
	}
	/** 修改新闻栏目 */	
	public boolean updateNewscolumns(Newscolumns columns){
		return newscolumnsDao.update(columns);
	}

	/** 浏览新闻栏目 */
	public List<Newscolumns> browseNewscolumns(){
		return newscolumnsDao.listAll();
	}
	
	/** 一级新闻栏目列表 */
	/*public List<Newscolumns> listNewscolumns(){
//		return newscolumnsDao.listAll();
		return  newscolumnsDao.list("select * from newscolumns as a where a.newscolumns is null",new Object[]{});
	}*/
	
	/** 下级新闻栏目列表 */
	/*@SuppressWarnings("unchecked")
	public List<Newscolumns> listChildColumns(Newscolumns columns){
		if (columns==null){
			return null;
		}else{
			return newscolumnsDao.query("from newscolumns as a where a.newscolumns.id="+columns.getId());
		}		
	}*/

	/** 删除指定的新闻栏目 */
	public boolean deleteNewscolumns(Integer id){
		return newscolumnsDao.delete(id);
	}

	/** 装载指定的新闻栏目 */
	public Newscolumns loadNewscolumns(Integer id){
		return (Newscolumns)newscolumnsDao.load(id);
	}

}
