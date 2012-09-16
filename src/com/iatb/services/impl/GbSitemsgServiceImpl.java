package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbSitemsgDao;
import com.iatb.pojo.GbSitemsg;
import com.iatb.services.GbSitemsgService;

/**
 * GbSitemsg管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbSitemsgService")
public class GbSitemsgServiceImpl implements GbSitemsgService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbSitemsgDao gbSitemsgDao;
	/** 新增GbSitemsg */	
	public boolean saveGbSitemsg(GbSitemsg columns){
			return gbSitemsgDao.save(columns);
	}
	/** 修改GbSitemsg */	
	public boolean updateGbSitemsg(GbSitemsg columns){
		return gbSitemsgDao.update(columns);
	}

	/** 浏览GbSitemsg */
	public List<GbSitemsg> browseGbSitemsg(){
		return gbSitemsgDao.listAll();
	}
	
	/** 删除指定的GbSitemsg */
	public boolean deleteGbSitemsg(Integer id){
		return gbSitemsgDao.delete(id);
	}

	/** 装载指定的GbSitemsg */
	public GbSitemsg loadGbSitemsg(Integer id){
		return (GbSitemsg)gbSitemsgDao.load(id);
	}

}
