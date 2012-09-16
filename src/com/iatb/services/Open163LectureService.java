package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Lecture;

/**
 * Open163Lecture管理业务逻辑接口
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163LectureService {
	/** 浏览 Open163Lecture*/
	public List<Open163Lecture> browseOpen163Lecture();
	/** 装载指定的 Open163Lecture*/
	public Open163Lecture loadOpen163Lecture(Integer id);
	/** 删除指定的 Open163Lecture*/	
	public boolean deleteOpen163Lecture(Integer id);
	/** 新增 Open163Lecture*/
	public boolean saveOpen163Lecture(Open163Lecture open163Lecture);
	/** 修改 Open163Lecture*/	
	public boolean updateOpen163Lecture(Open163Lecture open163Lecture);
	/** 更新Open163Lecture:id的状态 */
	public void updateStatus(int id,int status);
}