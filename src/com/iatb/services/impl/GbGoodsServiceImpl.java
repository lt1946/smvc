package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import com.iatb.dao.GbGoodsDao;
import com.iatb.pojo.GbGoods;
import com.iatb.services.GbGoodsService;

/**
 * GbGoods����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbGoodsService")
public class GbGoodsServiceImpl implements GbGoodsService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbGoodsDao gbGoodsDao;
	/** ����GbGoods */	
	public boolean saveGbGoods(GbGoods columns){
			return gbGoodsDao.save(columns);
	}
	/** �޸�GbGoods */	
	public boolean updateGbGoods(GbGoods columns){
		return gbGoodsDao.update(columns);
	}

	/** ���GbGoods */
	public List<GbGoods> browseGbGoods(){
		return gbGoodsDao.listAll();
	}
	
	/** ɾ��ָ����GbGoods */
	public boolean deleteGbGoods(Integer id){
		return gbGoodsDao.delete(id);
	}

	/** װ��ָ����GbGoods */
	public GbGoods loadGbGoods(Integer id){
		return (GbGoods)gbGoodsDao.load(id);
	}
	public boolean existByUrl(int siteid, String loc) {
		return gbGoodsDao.count("select count(1) from gb_goods where siteid=? and url=? and isExpired=0",new Object[]{siteid,loc})>0;
	}
	public void updateExpired() {
		String now=String.valueOf(new Date().getTime()).substring(0,10);
		gbGoodsDao.execute("update gb_goods set isExpired=1 where isExpired=0 and enddate<=?",new Object[]{now});
	}

}
