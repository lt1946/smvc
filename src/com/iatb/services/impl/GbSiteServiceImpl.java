package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbSiteDao;
import com.iatb.pojo.GbSite;
import com.iatb.services.GbSiteService;

/**
 * GbSite管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbSiteService")
public class GbSiteServiceImpl implements GbSiteService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbSiteDao gbSiteDao;
	/** 新增GbSite */	
	public boolean saveGbSite(GbSite columns){
			return gbSiteDao.save(columns);
	}
	/** 修改GbSite */	
	public boolean updateGbSite(GbSite columns){
		return gbSiteDao.update(columns);
	}
	/**
	 * 更新id的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		gbSiteDao.execute("update gb_site set status=? where id=?", new Object[]{status,id});
	}

	/** 浏览GbSite */
	public List<GbSite> browseGbSite(){
		return gbSiteDao.listAll();
	}
	
	/** 删除指定的GbSite */
	public boolean deleteGbSite(Integer id){
		return gbSiteDao.delete(id);
	}

	/** 装载指定的GbSite */
	public GbSite loadGbSite(Integer id){
		return (GbSite)gbSiteDao.load(id);
	}
	
	/**
	 * 查看id是否存在api
	 * @param id
	 * @return
	 */
	public boolean existApi(int id){
		return gbSiteDao.count("select count(1) from gb_site where gbsiteid = "+id)>0;
	}
	//TODO to serviceImpl template
	public void update(String name, Object o, int id) {
		gbSiteDao.execute("update gb_site set "+name+"=? where id=?",new Object[]{o.toString(),id});
	}
	/**
	 * 重置今天的状态
	 */
	public void updateTodayAll(){
		gbSiteDao.execute("update gb_site set status=0 where status=10", null);
	}
}
