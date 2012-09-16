package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbSitemsg;

/**
 * GbSitemsg管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbSitemsgService {
	/** 浏览 GbSitemsg*/
	public List<GbSitemsg> browseGbSitemsg();
	/** 装载指定的 GbSitemsg*/
	public GbSitemsg loadGbSitemsg(Integer id);
	/** 删除指定的 GbSitemsg*/	
	public boolean deleteGbSitemsg(Integer id);
	/** 新增 GbSitemsg*/
	public boolean saveGbSitemsg(GbSitemsg gbSitemsg);
	/** 修改 GbSitemsg*/	
	public boolean updateGbSitemsg(GbSitemsg gbSitemsg);
}