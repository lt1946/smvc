package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbBuy;

/**
 * GbBuy管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbBuyService {
	/** 浏览 GbBuy*/
	public List<GbBuy> browseGbBuy();
	/** 装载指定的 GbBuy*/
	public GbBuy loadGbBuy(Integer id);
	/** 删除指定的 GbBuy*/	
	public boolean deleteGbBuy(Integer id);
	/** 新增 GbBuy*/
	public boolean saveGbBuy(GbBuy gbBuy);
	/** 修改 GbBuy*/	
	public boolean updateGbBuy(GbBuy gbBuy);
}