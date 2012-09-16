package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.WbcontentDao;
import com.iatb.pojo.Wbcontent;
import com.iatb.services.WbcontentService;

/**
 * Wbcontent����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-01-15 20:29:46
 */
@Service("wbcontentService")
public class WbcontentServiceImpl implements WbcontentService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	WbcontentDao wbcontentDao;
	/** ����Wbcontent */	
	public boolean saveWbcontent(Wbcontent columns){
			return wbcontentDao.save(columns);
	}
	/** �޸�Wbcontent */	
	public boolean updateWbcontent(Wbcontent columns){
		return wbcontentDao.update(columns);
	}

	/** ���Wbcontent */
	public List<Wbcontent> browseWbcontent(){
		return wbcontentDao.listAll();
	}
	
	/** ɾ��ָ����Wbcontent */
	public boolean deleteWbcontent(Integer id){
		return wbcontentDao.delete(id);
	}

	/** װ��ָ����Wbcontent */
	public Wbcontent loadWbcontent(Integer id){
		return (Wbcontent)wbcontentDao.load(id);
	}

}
