package com.iatb.services;

import java.util.List;
import com.iatb.pojo.GbUser;

/**
 * GbUser����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
public interface GbUserService {
	/** ��� GbUser*/
	public List<GbUser> browseGbUser();
	/** װ��ָ���� GbUser*/
	public GbUser loadGbUser(Integer id);
	/** ɾ��ָ���� GbUser*/	
	public boolean deleteGbUser(Integer id);
	/** ���� GbUser*/
	public boolean saveGbUser(GbUser gbUser);
	/** �޸� GbUser*/	
	public boolean updateGbUser(GbUser gbUser);
}