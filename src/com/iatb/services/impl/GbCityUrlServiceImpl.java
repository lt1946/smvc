package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbCityUrlDao;
import com.iatb.pojo.GbCityUrl;
import com.iatb.services.GbCityUrlService;

/**
 * GbCityUrl����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbCityUrlService")
public class GbCityUrlServiceImpl implements GbCityUrlService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbCityUrlDao gbCityUrlDao;
	/** ����GbCityUrl */	
	public boolean saveGbCityUrl(GbCityUrl columns){
			return gbCityUrlDao.save(columns);
	}
	/** �޸�GbCityUrl */	
	public boolean updateGbCityUrl(GbCityUrl columns){
		return gbCityUrlDao.update(columns);
	}

	/** ���GbCityUrl */
	public List<GbCityUrl> browseGbCityUrl(){
		return gbCityUrlDao.listAll();
	}
	
	/** ɾ��ָ����GbCityUrl */
	public boolean deleteGbCityUrl(Integer id){
		return gbCityUrlDao.delete(id);
	}

	/** װ��ָ����GbCityUrl */
	public GbCityUrl loadGbCityUrl(Integer id){
		return (GbCityUrl)gbCityUrlDao.load(id);
	}

}
