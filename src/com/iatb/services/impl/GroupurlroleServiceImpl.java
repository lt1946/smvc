package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GroupurlroleDao;
import com.iatb.pojo.Groupurlrole;
import com.iatb.services.GroupurlroleService;

/**
 * Groupurlrole����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-01-23 00:45:11
 */
@Service("groupurlroleService")
public class GroupurlroleServiceImpl implements GroupurlroleService {
	
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	GroupurlroleDao groupurlroleDao;
	/** ����Groupurlrole */	
	public boolean saveGroupurlrole(Groupurlrole columns){
			return groupurlroleDao.save(columns);
	}
	/** �޸�Groupurlrole */	
	public boolean updateGroupurlrole(Groupurlrole columns){
		return groupurlroleDao.update(columns);
	}

	/** ���Groupurlrole */
	public List<Groupurlrole> browseGroupurlrole(){
		return groupurlroleDao.listAll();
	}
	
	/** ɾ��ָ����Groupurlrole */
	public boolean deleteGroupurlrole(Integer id){
		return groupurlroleDao.delete(id);
	}

	/** װ��ָ����Groupurlrole */
	public Groupurlrole loadGroupurlrole(Integer id){
		return (Groupurlrole)groupurlroleDao.load(id);
	}
	
	/** ���Groupurlrole */
	public List<Groupurlrole> view(){
		return groupurlroleDao.view();
	}
	/** ��ҳ���Groupurlrole */
	public List<Groupurlrole> view(int page,int row){
		return groupurlroleDao.view(page,row);
	}
	public Long count() {
		return  groupurlroleDao.count("select count(1) from groupurlrole");
	}
	/** ����id�ļ�¼	 */
	public boolean copyById(String id) {
		return groupurlroleDao.copyById(id);
	}
	/** ɾ��id�ļ�¼	 */
	public boolean deleteById(String id) {
		return groupurlroleDao.deletes(id);
	}
}
