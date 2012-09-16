package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163LectureDao;
import com.iatb.pojo.Open163Lecture;
import com.iatb.services.Open163LectureService;
import org.apache.log4j.Logger;

/**
 * Open163Lecture管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-04-02 23:12:10
 */
@Service("open163LectureService")
public class Open163LectureServiceImpl implements Open163LectureService {

	private static final Logger log=Logger.getLogger(Open163LectureServiceImpl.class);
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	Open163LectureDao open163LectureDao;
	/** 新增Open163Lecture */	
	public boolean saveOpen163Lecture(Open163Lecture columns){
			return open163LectureDao.save(columns);
	}
	/** 修改Open163Lecture */	
	public boolean updateOpen163Lecture(Open163Lecture columns){
		return open163LectureDao.update(columns);
	}

	/** 浏览Open163Lecture */
	public List<Open163Lecture> browseOpen163Lecture(){
		return open163LectureDao.listAll();
	}
	
	/** 删除指定的Open163Lecture */
	public boolean deleteOpen163Lecture(Integer id){
		return open163LectureDao.delete(id);
	}

	/** 装载指定的Open163Lecture */
	public Open163Lecture loadOpen163Lecture(Integer id){
		return (Open163Lecture)open163LectureDao.load(id);
	}

	/**
	 * 更新Open163Lecture:id的状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163LectureDao.updateStatus(id,status);
	}
}
