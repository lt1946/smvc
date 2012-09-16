package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Wbcontent;

/**
 * Wbcontent管理业务逻辑接口
 * @author Administrator
 * @since  2012-01-15 20:29:46
 */
public interface WbcontentService {
	/** 浏览 Wbcontent*/
	public List<Wbcontent> browseWbcontent();
	/** 装载指定的 Wbcontent*/
	public Wbcontent loadWbcontent(Integer id);
	/** 删除指定的 Wbcontent*/	
	public boolean deleteWbcontent(Integer id);
	/** 新增 Wbcontent*/
	public boolean saveWbcontent(Wbcontent wbcontent);
	/** 修改 Wbcontent*/	
	public boolean updateWbcontent(Wbcontent wbcontent);
}