package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.GbRegeditDao;
import com.iatb.pojo.GbRegedit;
import com.iatb.services.GbRegeditService;

/**
 * GbRegedit管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Service("gbRegeditService")
public class GbRegeditServiceImpl implements GbRegeditService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	GbRegeditDao gbRegeditDao;
	/** 新增GbRegedit */	
	public boolean saveGbRegedit(GbRegedit columns){
			return gbRegeditDao.save(columns);
	}
	/** 修改GbRegedit */	
	public boolean updateGbRegedit(GbRegedit columns){
		return gbRegeditDao.update(columns);
	}

	/** 浏览GbRegedit */
	public List<GbRegedit> browseGbRegedit(){
		return gbRegeditDao.listAll();
	}
	
	/** 删除指定的GbRegedit */
	public boolean deleteGbRegedit(Integer id){
		return gbRegeditDao.delete(id);
	}

	/** 装载指定的GbRegedit */
	public GbRegedit loadGbRegedit(Integer id){
		return (GbRegedit)gbRegeditDao.load(id);
	}

}
