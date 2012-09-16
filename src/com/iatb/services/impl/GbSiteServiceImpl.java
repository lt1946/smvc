package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbSiteDao;
import com.iatb.pojo.GbSite;
import com.iatb.services.GbSiteService;

/**
 * GbSite����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbSiteService")
public class GbSiteServiceImpl implements GbSiteService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GbSiteDao gbSiteDao;
	/** ����GbSite */	
	public boolean saveGbSite(GbSite columns){
			return gbSiteDao.save(columns);
	}
	/** �޸�GbSite */	
	public boolean updateGbSite(GbSite columns){
		return gbSiteDao.update(columns);
	}
	/**
	 * ����id��״̬
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		gbSiteDao.execute("update gb_site set status=? where id=?", new Object[]{status,id});
	}

	/** ���GbSite */
	public List<GbSite> browseGbSite(){
		return gbSiteDao.listAll();
	}
	
	/** ɾ��ָ����GbSite */
	public boolean deleteGbSite(Integer id){
		return gbSiteDao.delete(id);
	}

	/** װ��ָ����GbSite */
	public GbSite loadGbSite(Integer id){
		return (GbSite)gbSiteDao.load(id);
	}
	
	/**
	 * �鿴id�Ƿ����api
	 * @param id
	 * @return
	 */
	public boolean existApi(int id){
		return gbSiteDao.count("select count(1) from gb_site where gbsiteid = "+id)>0;
	}
	//TODO to serviceImpl template
	public void update(String name, Object o, int id) {
		gbSiteDao.execute("update gb_site set "+name+"=? where id=?",new Object[]{o.toString(),id});
	}
	/**
	 * ���ý����״̬
	 */
	public void updateTodayAll(){
		gbSiteDao.execute("update gb_site set status=0 where status=10", null);
	}
}
