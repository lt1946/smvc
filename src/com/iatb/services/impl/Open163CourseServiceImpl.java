package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163CourseDao;
import com.iatb.pojo.Open163Course;
import com.iatb.services.Open163CourseService;
import org.apache.log4j.Logger;

/**
 * Open163Course管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-04-02 23:12:10
 */
@Service("open163CourseService")
public class Open163CourseServiceImpl implements Open163CourseService {

	private static final Logger log=Logger.getLogger(Open163CourseServiceImpl.class);
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	Open163CourseDao open163CourseDao;
	/** 新增Open163Course */	
	public boolean saveOpen163Course(Open163Course columns){
			return open163CourseDao.save(columns);
	}
	/** 修改Open163Course */	
	public boolean updateOpen163Course(Open163Course columns){
		return open163CourseDao.update(columns);
	}

	/** 浏览Open163Course */
	public List<Open163Course> browseOpen163Course(){
		return open163CourseDao.listAll();
	}
	
	/** 删除指定的Open163Course */
	public boolean deleteOpen163Course(Integer id){
		return open163CourseDao.delete(id);
	}

	/** 装载指定的Open163Course */
	public Open163Course loadOpen163Course(Integer id){
		return (Open163Course)open163CourseDao.load(id);
	}

	/**
	 * 更新Open163Course:id的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163CourseDao.updateStatus(id,status);
	}
}
