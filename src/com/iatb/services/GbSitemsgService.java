package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbSitemsg;

/**
 * GbSitemsg����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbSitemsgService {
	/** ��� GbSitemsg*/
	public List<GbSitemsg> browseGbSitemsg();
	/** װ��ָ���� GbSitemsg*/
	public GbSitemsg loadGbSitemsg(Integer id);
	/** ɾ��ָ���� GbSitemsg*/	
	public boolean deleteGbSitemsg(Integer id);
	/** ���� GbSitemsg*/
	public boolean saveGbSitemsg(GbSitemsg gbSitemsg);
	/** �޸� GbSitemsg*/	
	public boolean updateGbSitemsg(GbSitemsg gbSitemsg);
}