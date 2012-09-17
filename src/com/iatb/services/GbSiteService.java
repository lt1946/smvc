package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbSite;

/**
 * GbSite管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbSiteService {
	/** 浏览 GbSite*/
	public List<GbSite> browseGbSite();
	/** 装载指定的 GbSite*/
	public GbSite loadGbSite(Integer id);
	/** 删除指定的 GbSite*/	
	public boolean deleteGbSite(Integer id);
	/** 新增 GbSite*/
	public boolean saveGbSite(GbSite gbSite);
	/** 修改 GbSite*/	
	public boolean updateGbSite(GbSite gbSite);
	/** 查看id是否存在api */
	public boolean existApi(int id);
	/** 更新id的状态 */
	public void updateStatus(int id,int status);
	/** 更新id的字段name的值 */
	public void update(String name,Object[] o);
	/** 重置今天的状态 */
	public void updateTodayAll();
}