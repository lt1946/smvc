package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbSiteCity;

/**
 * GbSiteCity����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbSiteCityService {
	/** ��� GbSiteCity*/
	public List<GbSiteCity> browseGbSiteCity();
	/** װ��ָ���� GbSiteCity*/
	public GbSiteCity loadGbSiteCity(Integer id);
	/** ɾ��ָ���� GbSiteCity*/	
	public boolean deleteGbSiteCity(Integer id);
	/** ���� GbSiteCity*/
	public boolean saveGbSiteCity(GbSiteCity gbSiteCity);
	/** �޸� GbSiteCity*/	
	public boolean updateGbSiteCity(GbSiteCity gbSiteCity);
}