package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GroupurlroleDao;
import com.iatb.pojo.Groupurlrole;
import com.iatb.services.GroupurlroleService;

/**
 * Groupurlrole管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-01-23 00:45:11
 */
@Service("groupurlroleService")
public class GroupurlroleServiceImpl implements GroupurlroleService {
	
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GroupurlroleDao groupurlroleDao;
	/** 新增Groupurlrole */	
	public boolean saveGroupurlrole(Groupurlrole columns){
			return groupurlroleDao.save(columns);
	}
	/** 修改Groupurlrole */	
	public boolean updateGroupurlrole(Groupurlrole columns){
		return groupurlroleDao.update(columns);
	}

	/** 浏览Groupurlrole */
	public List<Groupurlrole> browseGroupurlrole(){
		return groupurlroleDao.listAll();
	}
	
	/** 删除指定的Groupurlrole */
	public boolean deleteGroupurlrole(Integer id){
		return groupurlroleDao.delete(id);
	}

	/** 装载指定的Groupurlrole */
	public Groupurlrole loadGroupurlrole(Integer id){
		return (Groupurlrole)groupurlroleDao.load(id);
	}
	
	/** 浏览Groupurlrole */
	public List<Groupurlrole> view(){
		return groupurlroleDao.view();
	}
	/** 分页浏览Groupurlrole */
	public List<Groupurlrole> view(int page,int row){
		return groupurlroleDao.view(page,row);
	}
	public Long count() {
		return  groupurlroleDao.count("select count(1) from groupurlrole");
	}
	/** 复制id的记录	 */
	public boolean copyById(String id) {
		return groupurlroleDao.copyById(id);
	}
	/** 删除id的记录	 */
	public boolean deleteById(String id) {
		return groupurlroleDao.deletes(id);
	}
}
