package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbBuyDao;
import com.iatb.pojo.GbBuy;
import com.iatb.services.GbBuyService;

/**
 * GbBuy����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbBuyService")
public class GbBuyServiceImpl implements GbBuyService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbBuyDao gbBuyDao;
	/** ����GbBuy */	
	public boolean saveGbBuy(GbBuy columns){
			return gbBuyDao.save(columns);
	}
	/** �޸�GbBuy */	
	public boolean updateGbBuy(GbBuy columns){
		return gbBuyDao.update(columns);
	}

	/** ���GbBuy */
	public List<GbBuy> browseGbBuy(){
		return gbBuyDao.listAll();
	}
	
	/** ɾ��ָ����GbBuy */
	public boolean deleteGbBuy(Integer id){
		return gbBuyDao.delete(id);
	}

	/** װ��ָ����GbBuy */
	public GbBuy loadGbBuy(Integer id){
		return (GbBuy)gbBuyDao.load(id);
	}

}
