package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbRegeditDao;
import com.iatb.pojo.GbRegedit;
import com.iatb.services.GbRegeditService;

/**
 * GbRegedit����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbRegeditService")
public class GbRegeditServiceImpl implements GbRegeditService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbRegeditDao gbRegeditDao;
	/** ����GbRegedit */	
	public boolean saveGbRegedit(GbRegedit columns){
			return gbRegeditDao.save(columns);
	}
	/** �޸�GbRegedit */	
	public boolean updateGbRegedit(GbRegedit columns){
		return gbRegeditDao.update(columns);
	}

	/** ���GbRegedit */
	public List<GbRegedit> browseGbRegedit(){
		return gbRegeditDao.listAll();
	}
	
	/** ɾ��ָ����GbRegedit */
	public boolean deleteGbRegedit(Integer id){
		return gbRegeditDao.delete(id);
	}

	/** װ��ָ����GbRegedit */
	public GbRegedit loadGbRegedit(Integer id){
		return (GbRegedit)gbRegeditDao.load(id);
	}

}
