package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import com.iatb.dao.GbGoodsDao;
import com.iatb.pojo.GbGoods;
import com.iatb.services.GbGoodsService;

/**
 * GbGoods管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbGoodsService")
public class GbGoodsServiceImpl implements GbGoodsService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbGoodsDao gbGoodsDao;
	/** 新增GbGoods */	
	public boolean saveGbGoods(GbGoods columns){
			return gbGoodsDao.save(columns);
	}
	/** 修改GbGoods */	
	public boolean updateGbGoods(GbGoods columns){
		return gbGoodsDao.update(columns);
	}

	/** 浏览GbGoods */
	public List<GbGoods> browseGbGoods(){
		return gbGoodsDao.listAll();
	}
	
	/** 删除指定的GbGoods */
	public boolean deleteGbGoods(Integer id){
		return gbGoodsDao.delete(id);
	}

	/** 装载指定的GbGoods */
	public GbGoods loadGbGoods(Integer id){
		return (GbGoods)gbGoodsDao.load(id);
	}
	public boolean existByUrl(int siteid, String loc) {
		return gbGoodsDao.count("select count(1) from gb_goods where siteid=? and url=? and isExpired=0",new Object[]{siteid,loc})>0;
	}
	public void updateExpired() {
		String now=String.valueOf(new Date().getTime()).substring(0,10);
		gbGoodsDao.execute("update gb_goods set isExpired=1 where isExpired=0 and enddate<=?",new Object[]{now});
	}

}
