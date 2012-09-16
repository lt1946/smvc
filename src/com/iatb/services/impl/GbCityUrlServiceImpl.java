package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbCityUrlDao;
import com.iatb.pojo.GbCityUrl;
import com.iatb.services.GbCityUrlService;

/**
 * GbCityUrl管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbCityUrlService")
public class GbCityUrlServiceImpl implements GbCityUrlService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbCityUrlDao gbCityUrlDao;
	/** 新增GbCityUrl */	
	public boolean saveGbCityUrl(GbCityUrl columns){
			return gbCityUrlDao.save(columns);
	}
	/** 修改GbCityUrl */	
	public boolean updateGbCityUrl(GbCityUrl columns){
		return gbCityUrlDao.update(columns);
	}

	/** 浏览GbCityUrl */
	public List<GbCityUrl> browseGbCityUrl(){
		return gbCityUrlDao.listAll();
	}
	
	/** 删除指定的GbCityUrl */
	public boolean deleteGbCityUrl(Integer id){
		return gbCityUrlDao.delete(id);
	}

	/** 装载指定的GbCityUrl */
	public GbCityUrl loadGbCityUrl(Integer id){
		return (GbCityUrl)gbCityUrlDao.load(id);
	}

}
