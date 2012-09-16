package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbCityUrl;

/**
 * GbCityUrl管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbCityUrlService {
	/** 浏览 GbCityUrl*/
	public List<GbCityUrl> browseGbCityUrl();
	/** 装载指定的 GbCityUrl*/
	public GbCityUrl loadGbCityUrl(Integer id);
	/** 删除指定的 GbCityUrl*/	
	public boolean deleteGbCityUrl(Integer id);
	/** 新增 GbCityUrl*/
	public boolean saveGbCityUrl(GbCityUrl gbCityUrl);
	/** 修改 GbCityUrl*/	
	public boolean updateGbCityUrl(GbCityUrl gbCityUrl);
}