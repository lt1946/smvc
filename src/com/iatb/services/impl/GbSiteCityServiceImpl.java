package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbSiteCityDao;
import com.iatb.pojo.GbSiteCity;
import com.iatb.services.GbSiteCityService;

/**
 * GbSiteCity管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbSiteCityService")
public class GbSiteCityServiceImpl implements GbSiteCityService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbSiteCityDao gbSiteCityDao;
	/** 新增GbSiteCity */	
	public boolean saveGbSiteCity(GbSiteCity columns){
			return gbSiteCityDao.save(columns);
	}
	/** 修改GbSiteCity */	
	public boolean updateGbSiteCity(GbSiteCity columns){
		return gbSiteCityDao.update(columns);
	}

	/** 浏览GbSiteCity */
	public List<GbSiteCity> browseGbSiteCity(){
		return gbSiteCityDao.listAll();
	}
	
	/** 删除指定的GbSiteCity */
	public boolean deleteGbSiteCity(Integer id){
		return gbSiteCityDao.delete(id);
	}

	/** 装载指定的GbSiteCity */
	public GbSiteCity loadGbSiteCity(Integer id){
		return (GbSiteCity)gbSiteCityDao.load(id);
	}

}
