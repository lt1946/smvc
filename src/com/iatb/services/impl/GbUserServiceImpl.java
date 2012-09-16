package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbUserDao;
import com.iatb.pojo.GbUser;
import com.iatb.services.GbUserService;

/**
 * GbUser����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbUserService")
public class GbUserServiceImpl implements GbUserService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbUserDao gbUserDao;
	/** ����GbUser */	
	public boolean saveGbUser(GbUser columns){
			return gbUserDao.save(columns);
	}
	/** �޸�GbUser */	
	public boolean updateGbUser(GbUser columns){
		return gbUserDao.update(columns);
	}

	/** ���GbUser */
	public List<GbUser> browseGbUser(){
		return gbUserDao.listAll();
	}
	
	/** ɾ��ָ����GbUser */
	public boolean deleteGbUser(Integer id){
		return gbUserDao.delete(id);
	}

	/** װ��ָ����GbUser */
	public GbUser loadGbUser(Integer id){
		return (GbUser)gbUserDao.load(id);
	}

}
