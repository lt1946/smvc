package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbUserDao;
import com.iatb.pojo.GbUser;
import com.iatb.services.GbUserService;

/**
 * GbUser管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbUserService")
public class GbUserServiceImpl implements GbUserService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbUserDao gbUserDao;
	/** 新增GbUser */	
	public boolean saveGbUser(GbUser columns){
			return gbUserDao.save(columns);
	}
	/** 修改GbUser */	
	public boolean updateGbUser(GbUser columns){
		return gbUserDao.update(columns);
	}

	/** 浏览GbUser */
	public List<GbUser> browseGbUser(){
		return gbUserDao.listAll();
	}
	
	/** 删除指定的GbUser */
	public boolean deleteGbUser(Integer id){
		return gbUserDao.delete(id);
	}

	/** 装载指定的GbUser */
	public GbUser loadGbUser(Integer id){
		return (GbUser)gbUserDao.load(id);
	}

}
