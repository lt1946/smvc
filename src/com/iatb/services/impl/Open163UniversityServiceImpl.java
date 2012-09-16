package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163UniversityDao;
import com.iatb.pojo.Open163University;
import com.iatb.services.Open163UniversityService;
import org.apache.log4j.Logger;

/**
 * Open163University管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-04-02 23:12:11
 */
@Service("open163UniversityService")
public class Open163UniversityServiceImpl implements Open163UniversityService {

	private static final Logger log=Logger.getLogger(Open163UniversityServiceImpl.class);
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	Open163UniversityDao open163UniversityDao;
	/** 新增Open163University */	
	public boolean saveOpen163University(Open163University columns){
			return open163UniversityDao.save(columns);
	}
	/** 修改Open163University */	
	public boolean updateOpen163University(Open163University columns){
		return open163UniversityDao.update(columns);
	}

	/** 浏览Open163University */
	public List<Open163University> browseOpen163University(){
		return open163UniversityDao.listAll();
	}
	
	/** 删除指定的Open163University */
	public boolean deleteOpen163University(Integer id){
		return open163UniversityDao.delete(id);
	}

	/** 装载指定的Open163University */
	public Open163University loadOpen163University(Integer id){
		return (Open163University)open163UniversityDao.load(id);
	}

	/**
	 * 更新Open163University:id的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163UniversityDao.updateStatus(id,status);
	}
}
