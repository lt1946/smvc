package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbRegedit;

/**
 * GbRegedit管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbRegeditService {
	/** 浏览 GbRegedit*/
	public List<GbRegedit> browseGbRegedit();
	/** 装载指定的 GbRegedit*/
	public GbRegedit loadGbRegedit(Integer id);
	/** 删除指定的 GbRegedit*/	
	public boolean deleteGbRegedit(Integer id);
	/** 新增 GbRegedit*/
	public boolean saveGbRegedit(GbRegedit gbRegedit);
	/** 修改 GbRegedit*/	
	public boolean updateGbRegedit(GbRegedit gbRegedit);
}