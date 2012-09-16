package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbSiteCityDao;
import com.iatb.pojo.GbSiteCity;
import com.iatb.services.GbSiteCityService;

/**
 * GbSiteCity����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbSiteCityService")
public class GbSiteCityServiceImpl implements GbSiteCityService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbSiteCityDao gbSiteCityDao;
	/** ����GbSiteCity */	
	public boolean saveGbSiteCity(GbSiteCity columns){
			return gbSiteCityDao.save(columns);
	}
	/** �޸�GbSiteCity */	
	public boolean updateGbSiteCity(GbSiteCity columns){
		return gbSiteCityDao.update(columns);
	}

	/** ���GbSiteCity */
	public List<GbSiteCity> browseGbSiteCity(){
		return gbSiteCityDao.listAll();
	}
	
	/** ɾ��ָ����GbSiteCity */
	public boolean deleteGbSiteCity(Integer id){
		return gbSiteCityDao.delete(id);
	}

	/** װ��ָ����GbSiteCity */
	public GbSiteCity loadGbSiteCity(Integer id){
		return (GbSiteCity)gbSiteCityDao.load(id);
	}

}
