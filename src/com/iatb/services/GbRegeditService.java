package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbRegedit;

/**
 * GbRegedit����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbRegeditService {
	/** ��� GbRegedit*/
	public List<GbRegedit> browseGbRegedit();
	/** װ��ָ���� GbRegedit*/
	public GbRegedit loadGbRegedit(Integer id);
	/** ɾ��ָ���� GbRegedit*/	
	public boolean deleteGbRegedit(Integer id);
	/** ���� GbRegedit*/
	public boolean saveGbRegedit(GbRegedit gbRegedit);
	/** �޸� GbRegedit*/	
	public boolean updateGbRegedit(GbRegedit gbRegedit);
}