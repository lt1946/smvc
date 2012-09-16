package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbApi;

/**
 * GbApi����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbApiService {
	/** ��� GbApi*/
	public List<GbApi> browseGbApi();
	/** װ��ָ���� GbApi*/
	public GbApi loadGbApi(Integer id);
	/** ɾ��ָ���� GbApi*/	
	public boolean deleteGbApi(Integer id);
	/** ���� GbApi*/
	public boolean saveGbApi(GbApi gbApi);
	/** �޸� GbApi*/	
	public boolean updateGbApi(GbApi gbApi);
	/** ͨ��siteid��ȡgbapi */
	public GbApi loadBySiteid(int gbsiteid);
	/** �����ֶ� */
	public void update(String string, Object i,int id);
	public int spider(GbApi api);
}