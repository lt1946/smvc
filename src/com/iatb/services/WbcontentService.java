package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Wbcontent;

/**
 * Wbcontent����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-01-15 20:29:46
 */
public interface WbcontentService {
	/** ��� Wbcontent*/
	public List<Wbcontent> browseWbcontent();
	/** װ��ָ���� Wbcontent*/
	public Wbcontent loadWbcontent(Integer id);
	/** ɾ��ָ���� Wbcontent*/	
	public boolean deleteWbcontent(Integer id);
	/** ���� Wbcontent*/
	public boolean saveWbcontent(Wbcontent wbcontent);
	/** �޸� Wbcontent*/	
	public boolean updateWbcontent(Wbcontent wbcontent);
}