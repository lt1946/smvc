package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Wz115url;

/**
 * Wz115url管理业务逻辑接口
 * @author Administrator
 * @since  2012-01-23 23:14:59
 */
public interface Wz115urlService {
	/** 浏览 Wz115url*/
	public List<Wz115url> browseWz115url();
	/** 装载指定的 Wz115url*/
	public Wz115url loadWz115url(Integer id);
	/** 删除指定的 Wz115url*/	
	public boolean deleteWz115url(Integer id);
	/** 新增 Wz115url*/
	public boolean saveWz115url(Wz115url wz115url);
	/** 修改 Wz115url*/	
	public boolean updateWz115url(Wz115url wz115url);
	public void spider115Wz();
}