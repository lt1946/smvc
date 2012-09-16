package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbInviteDao;
import com.iatb.pojo.GbInvite;
import com.iatb.services.GbInviteService;

/**
 * GbInvite����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbInviteService")
public class GbInviteServiceImpl implements GbInviteService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbInviteDao gbInviteDao;
	/** ����GbInvite */	
	public boolean saveGbInvite(GbInvite columns){
			return gbInviteDao.save(columns);
	}
	/** �޸�GbInvite */	
	public boolean updateGbInvite(GbInvite columns){
		return gbInviteDao.update(columns);
	}

	/** ���GbInvite */
	public List<GbInvite> browseGbInvite(){
		return gbInviteDao.listAll();
	}
	
	/** ɾ��ָ����GbInvite */
	public boolean deleteGbInvite(Integer id){
		return gbInviteDao.delete(id);
	}

	/** װ��ָ����GbInvite */
	public GbInvite loadGbInvite(Integer id){
		return (GbInvite)gbInviteDao.load(id);
	}

}
