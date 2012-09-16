package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbGoods;

/**
 * GbGoods管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbGoodsService {
	/** 浏览 GbGoods*/
	public List<GbGoods> browseGbGoods();
	/** 装载指定的 GbGoods*/
	public GbGoods loadGbGoods(Integer id);
	/** 删除指定的 GbGoods*/	
	public boolean deleteGbGoods(Integer id);
	/** 新增 GbGoods*/
	public boolean saveGbGoods(GbGoods gbGoods);
	/** 修改 GbGoods*/	
	public boolean updateGbGoods(GbGoods gbGoods);
	/** 是否存在 */
	public boolean existByUrl(int siteid, String loc);
	/** 更新过期 */
	public void updateExpired();
}