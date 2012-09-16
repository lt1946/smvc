package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbBuyDao;
import com.iatb.pojo.GbBuy;
import com.iatb.services.GbBuyService;

/**
 * GbBuy管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbBuyService")
public class GbBuyServiceImpl implements GbBuyService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbBuyDao gbBuyDao;
	/** 新增GbBuy */	
	public boolean saveGbBuy(GbBuy columns){
			return gbBuyDao.save(columns);
	}
	/** 修改GbBuy */	
	public boolean updateGbBuy(GbBuy columns){
		return gbBuyDao.update(columns);
	}

	/** 浏览GbBuy */
	public List<GbBuy> browseGbBuy(){
		return gbBuyDao.listAll();
	}
	
	/** 删除指定的GbBuy */
	public boolean deleteGbBuy(Integer id){
		return gbBuyDao.delete(id);
	}

	/** 装载指定的GbBuy */
	public GbBuy loadGbBuy(Integer id){
		return (GbBuy)gbBuyDao.load(id);
	}

}
