package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163OpenclasstypeDao;
import com.iatb.pojo.Open163Openclasstype;
import com.iatb.services.Open163OpenclasstypeService;
import org.apache.log4j.Logger;

/**
 * Open163Openclasstype管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-04-02 23:12:11
 */
@Service("open163OpenclasstypeService")
public class Open163OpenclasstypeServiceImpl implements Open163OpenclasstypeService {

	private static final Logger log=Logger.getLogger(Open163OpenclasstypeServiceImpl.class);
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	Open163OpenclasstypeDao open163OpenclasstypeDao;
	/** 新增Open163Openclasstype */	
	public boolean saveOpen163Openclasstype(Open163Openclasstype columns){
			return open163OpenclasstypeDao.save(columns);
	}
	/** 修改Open163Openclasstype */	
	public boolean updateOpen163Openclasstype(Open163Openclasstype columns){
		return open163OpenclasstypeDao.update(columns);
	}

	/** 浏览Open163Openclasstype */
	public List<Open163Openclasstype> browseOpen163Openclasstype(){
		return open163OpenclasstypeDao.listAll();
	}
	
	/** 删除指定的Open163Openclasstype */
	public boolean deleteOpen163Openclasstype(Integer id){
		return open163OpenclasstypeDao.delete(id);
	}

	/** 装载指定的Open163Openclasstype */
	public Open163Openclasstype loadOpen163Openclasstype(Integer id){
		return (Open163Openclasstype)open163OpenclasstypeDao.load(id);
	}

	/**
	 * 更新Open163Openclasstype:id的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163OpenclasstypeDao.updateStatus(id,status);
	}
}
