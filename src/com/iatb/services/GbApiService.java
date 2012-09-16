package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbApi;

/**
 * GbApi管理业务逻辑接口
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbApiService {
	/** 浏览 GbApi*/
	public List<GbApi> browseGbApi();
	/** 装载指定的 GbApi*/
	public GbApi loadGbApi(Integer id);
	/** 删除指定的 GbApi*/	
	public boolean deleteGbApi(Integer id);
	/** 新增 GbApi*/
	public boolean saveGbApi(GbApi gbApi);
	/** 修改 GbApi*/	
	public boolean updateGbApi(GbApi gbApi);
	/** 通过siteid获取gbapi */
	public GbApi loadBySiteid(int gbsiteid);
	/** 更新字段 */
	public void update(String string, Object i,int id);
	public int spider(GbApi api);
}