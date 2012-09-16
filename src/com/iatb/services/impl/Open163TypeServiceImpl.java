package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163TypeDao;
import com.iatb.pojo.Open163Type;
import com.iatb.services.Open163TypeService;
import org.apache.log4j.Logger;

/**
 * Open163Type管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-04-02 23:12:11
 */
@Service("open163TypeService")
public class Open163TypeServiceImpl implements Open163TypeService {

	private static final Logger log=Logger.getLogger(Open163TypeServiceImpl.class);
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	Open163TypeDao open163TypeDao;
	/** 新增Open163Type */	
	public boolean saveOpen163Type(Open163Type columns){
			return open163TypeDao.save(columns);
	}
	/** 修改Open163Type */	
	public boolean updateOpen163Type(Open163Type columns){
		return open163TypeDao.update(columns);
	}

	/** 浏览Open163Type */
	public List<Open163Type> browseOpen163Type(){
		return open163TypeDao.listAll();
	}
	
	/** 删除指定的Open163Type */
	public boolean deleteOpen163Type(Integer id){
		return open163TypeDao.delete(id);
	}

	/** 装载指定的Open163Type */
	public Open163Type loadOpen163Type(Integer id){
		return (Open163Type)open163TypeDao.load(id);
	}

	/**
	 * 更新Open163Type:id的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163TypeDao.updateStatus(id,status);
	}
}
