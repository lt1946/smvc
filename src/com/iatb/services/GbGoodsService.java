package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbGoods;

/**
 * GbGoods����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbGoodsService {
	/** ��� GbGoods*/
	public List<GbGoods> browseGbGoods();
	/** װ��ָ���� GbGoods*/
	public GbGoods loadGbGoods(Integer id);
	/** ɾ��ָ���� GbGoods*/	
	public boolean deleteGbGoods(Integer id);
	/** ���� GbGoods*/
	public boolean saveGbGoods(GbGoods gbGoods);
	/** �޸� GbGoods*/	
	public boolean updateGbGoods(GbGoods gbGoods);
	/** �Ƿ���� */
	public boolean existByUrl(int siteid, String loc);
	/** ���¹��� */
	public void updateExpired();
}