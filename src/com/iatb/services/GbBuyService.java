package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbBuy;

/**
 * GbBuy����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbBuyService {
	/** ��� GbBuy*/
	public List<GbBuy> browseGbBuy();
	/** װ��ָ���� GbBuy*/
	public GbBuy loadGbBuy(Integer id);
	/** ɾ��ָ���� GbBuy*/	
	public boolean deleteGbBuy(Integer id);
	/** ���� GbBuy*/
	public boolean saveGbBuy(GbBuy gbBuy);
	/** �޸� GbBuy*/	
	public boolean updateGbBuy(GbBuy gbBuy);
}