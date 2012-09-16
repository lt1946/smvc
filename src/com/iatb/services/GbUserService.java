package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbUser;

/**
 * GbUser管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbUserService {
	/** 浏览 GbUser*/
	public List<GbUser> browseGbUser();
	/** 装载指定的 GbUser*/
	public GbUser loadGbUser(Integer id);
	/** 删除指定的 GbUser*/	
	public boolean deleteGbUser(Integer id);
	/** 新增 GbUser*/
	public boolean saveGbUser(GbUser gbUser);
	/** 修改 GbUser*/	
	public boolean updateGbUser(GbUser gbUser);
}