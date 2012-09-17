package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbSite;

/**
 * GbSite����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbSiteService {
	/** ��� GbSite*/
	public List<GbSite> browseGbSite();
	/** װ��ָ���� GbSite*/
	public GbSite loadGbSite(Integer id);
	/** ɾ��ָ���� GbSite*/	
	public boolean deleteGbSite(Integer id);
	/** ���� GbSite*/
	public boolean saveGbSite(GbSite gbSite);
	/** �޸� GbSite*/	
	public boolean updateGbSite(GbSite gbSite);
	/** �鿴id�Ƿ����api */
	public boolean existApi(int id);
	/** ����id��״̬ */
	public void updateStatus(int id,int status);
	/** ����id���ֶ�name��ֵ */
	public void update(String name,Object[] o);
	/** ���ý����״̬ */
	public void updateTodayAll();
}