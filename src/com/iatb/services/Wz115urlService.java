package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Wz115url;

/**
 * Wz115url����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-01-23 23:14:59
 */
public interface Wz115urlService {
	/** ��� Wz115url*/
	public List<Wz115url> browseWz115url();
	/** װ��ָ���� Wz115url*/
	public Wz115url loadWz115url(Integer id);
	/** ɾ��ָ���� Wz115url*/	
	public boolean deleteWz115url(Integer id);
	/** ���� Wz115url*/
	public boolean saveWz115url(Wz115url wz115url);
	/** �޸� Wz115url*/	
	public boolean updateWz115url(Wz115url wz115url);
	public void spider115Wz();
}