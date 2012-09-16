package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbInvite;

/**
 * GbInvite管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbInviteService {
	/** 浏览 GbInvite*/
	public List<GbInvite> browseGbInvite();
	/** 装载指定的 GbInvite*/
	public GbInvite loadGbInvite(Integer id);
	/** 删除指定的 GbInvite*/	
	public boolean deleteGbInvite(Integer id);
	/** 新增 GbInvite*/
	public boolean saveGbInvite(GbInvite gbInvite);
	/** 修改 GbInvite*/	
	public boolean updateGbInvite(GbInvite gbInvite);
}