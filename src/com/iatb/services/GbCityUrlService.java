package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbCityUrl;

/**
 * GbCityUrl����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbCityUrlService {
	/** ��� GbCityUrl*/
	public List<GbCityUrl> browseGbCityUrl();
	/** װ��ָ���� GbCityUrl*/
	public GbCityUrl loadGbCityUrl(Integer id);
	/** ɾ��ָ���� GbCityUrl*/	
	public boolean deleteGbCityUrl(Integer id);
	/** ���� GbCityUrl*/
	public boolean saveGbCityUrl(GbCityUrl gbCityUrl);
	/** �޸� GbCityUrl*/	
	public boolean updateGbCityUrl(GbCityUrl gbCityUrl);
}