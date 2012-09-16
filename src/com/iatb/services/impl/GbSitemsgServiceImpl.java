package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbSitemsgDao;
import com.iatb.pojo.GbSitemsg;
import com.iatb.services.GbSitemsgService;

/**
 * GbSitemsg����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbSitemsgService")
public class GbSitemsgServiceImpl implements GbSitemsgService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbSitemsgDao gbSitemsgDao;
	/** ����GbSitemsg */	
	public boolean saveGbSitemsg(GbSitemsg columns){
			return gbSitemsgDao.save(columns);
	}
	/** �޸�GbSitemsg */	
	public boolean updateGbSitemsg(GbSitemsg columns){
		return gbSitemsgDao.update(columns);
	}

	/** ���GbSitemsg */
	public List<GbSitemsg> browseGbSitemsg(){
		return gbSitemsgDao.listAll();
	}
	
	/** ɾ��ָ����GbSitemsg */
	public boolean deleteGbSitemsg(Integer id){
		return gbSitemsgDao.delete(id);
	}

	/** װ��ָ����GbSitemsg */
	public GbSitemsg loadGbSitemsg(Integer id){
		return (GbSitemsg)gbSitemsgDao.load(id);
	}

}
