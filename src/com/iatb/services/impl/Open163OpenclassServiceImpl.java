package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163OpenclassDao;
import com.iatb.pojo.Open163Openclass;
import com.iatb.services.Open163OpenclassService;
import org.apache.log4j.Logger;

/**
 * Open163Openclass管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-04-02 23:12:10
 */
@Service("open163OpenclassService")
public class Open163OpenclassServiceImpl implements Open163OpenclassService {

	private static final Logger log=Logger.getLogger(Open163OpenclassServiceImpl.class);
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	Open163OpenclassDao open163OpenclassDao;
	/** 新增Open163Openclass */	
	public boolean saveOpen163Openclass(Open163Openclass columns){
			return open163OpenclassDao.save(columns);
	}
	/** 修改Open163Openclass */	
	public boolean updateOpen163Openclass(Open163Openclass columns){
		return open163OpenclassDao.update(columns);
	}

	/** 浏览Open163Openclass */
	public List<Open163Openclass> browseOpen163Openclass(){
		return open163OpenclassDao.listAll();
	}
	
	/** 删除指定的Open163Openclass */
	public boolean deleteOpen163Openclass(Integer id){
		return open163OpenclassDao.delete(id);
	}

	/** 装载指定的Open163Openclass */
	public Open163Openclass loadOpen163Openclass(Integer id){
		return (Open163Openclass)open163OpenclassDao.load(id);
	}

	/**
	 * 更新Open163Openclass:id的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163OpenclassDao.updateStatus(id,status);
	}
}
