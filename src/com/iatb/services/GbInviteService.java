package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbInvite;

/**
 * GbInvite����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbInviteService {
	/** ��� GbInvite*/
	public List<GbInvite> browseGbInvite();
	/** װ��ָ���� GbInvite*/
	public GbInvite loadGbInvite(Integer id);
	/** ɾ��ָ���� GbInvite*/	
	public boolean deleteGbInvite(Integer id);
	/** ���� GbInvite*/
	public boolean saveGbInvite(GbInvite gbInvite);
	/** �޸� GbInvite*/	
	public boolean updateGbInvite(GbInvite gbInvite);
}