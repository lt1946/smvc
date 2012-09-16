package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbInviteDao;
import com.iatb.pojo.GbInvite;
import com.iatb.services.GbInviteService;

/**
 * GbInvite管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbInviteService")
public class GbInviteServiceImpl implements GbInviteService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbInviteDao gbInviteDao;
	/** 新增GbInvite */	
	public boolean saveGbInvite(GbInvite columns){
			return gbInviteDao.save(columns);
	}
	/** 修改GbInvite */	
	public boolean updateGbInvite(GbInvite columns){
		return gbInviteDao.update(columns);
	}

	/** 浏览GbInvite */
	public List<GbInvite> browseGbInvite(){
		return gbInviteDao.listAll();
	}
	
	/** 删除指定的GbInvite */
	public boolean deleteGbInvite(Integer id){
		return gbInviteDao.delete(id);
	}

	/** 装载指定的GbInvite */
	public GbInvite loadGbInvite(Integer id){
		return (GbInvite)gbInviteDao.load(id);
	}

}
