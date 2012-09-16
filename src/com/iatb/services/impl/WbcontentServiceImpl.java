package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.WbcontentDao;
import com.iatb.pojo.Wbcontent;
import com.iatb.services.WbcontentService;

/**
 * Wbcontent管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-01-15 20:29:46
 */
@Service("wbcontentService")
public class WbcontentServiceImpl implements WbcontentService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	WbcontentDao wbcontentDao;
	/** 新增Wbcontent */	
	public boolean saveWbcontent(Wbcontent columns){
			return wbcontentDao.save(columns);
	}
	/** 修改Wbcontent */	
	public boolean updateWbcontent(Wbcontent columns){
		return wbcontentDao.update(columns);
	}

	/** 浏览Wbcontent */
	public List<Wbcontent> browseWbcontent(){
		return wbcontentDao.listAll();
	}
	
	/** 删除指定的Wbcontent */
	public boolean deleteWbcontent(Integer id){
		return wbcontentDao.delete(id);
	}

	/** 装载指定的Wbcontent */
	public Wbcontent loadWbcontent(Integer id){
		return (Wbcontent)wbcontentDao.load(id);
	}

}
