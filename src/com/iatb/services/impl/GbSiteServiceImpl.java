package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.bean.GBean;
import com.iatb.dao.GbSiteDao;
import com.iatb.pojo.GbSite;
import com.iatb.services.GbSiteService;
import com.iatb.util.DateUtil;

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
//	public void update(String name, Object o, int id) {
//		gbSiteDao.execute("update gb_site set "+name+"=? where id=?",new Object[]{o.toString(),id});
//	}
	//TODO Later add to serviceImpl template smvc
	/** �����ֶ� */
	public void update(String name, Object[] o){
		String ns[]=name.split(",");
		Object[] obj = new Object[ns.length+1];
		StringBuffer sql=new StringBuffer("update gb_site set ");
		for (int j = 0; j < ns.length; j++) {
			if(j!=ns.length-1)	sql.append(ns[j]+"=?,");
			else sql.append(ns[j]+"=? ");
			if (o[j] instanceof Integer) {
				obj[j]= Integer.valueOf(o[j].toString());
			}else if (o[j] instanceof String) {
				obj[j]= o[j].toString();
			}		//TODO add other obj type. smvc
		}
		obj[ns.length]=o[ns.length];
		sql.append(" where id=?");
		gbSiteDao.execute(sql.toString(),obj);		
	}
	/**
	 * ���ó��˽����Ѿ�ץȡ��״̬��δץȡ0.
	 */
	public void resetStatusButToday(){
		gbSiteDao.execute("update gb_site set status=0 where status="+GBean.STATUS_ALREALDYSIPDER+" and updateTime not like '%"+DateUtil.getCurrentDate()+"%'" , null);
	}
}
