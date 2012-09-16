package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbSiteCity;

/**
 * GbSiteCity管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbSiteCityService {
	/** 浏览 GbSiteCity*/
	public List<GbSiteCity> browseGbSiteCity();
	/** 装载指定的 GbSiteCity*/
	public GbSiteCity loadGbSiteCity(Integer id);
	/** 删除指定的 GbSiteCity*/	
	public boolean deleteGbSiteCity(Integer id);
	/** 新增 GbSiteCity*/
	public boolean saveGbSiteCity(GbSiteCity gbSiteCity);
	/** 修改 GbSiteCity*/	
	public boolean updateGbSiteCity(GbSiteCity gbSiteCity);
}