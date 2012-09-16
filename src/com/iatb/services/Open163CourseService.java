package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Course;

/**
 * Open163Course管理业务逻辑接口
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163CourseService {
	/** 浏览 Open163Course*/
	public List<Open163Course> browseOpen163Course();
	/** 装载指定的 Open163Course*/
	public Open163Course loadOpen163Course(Integer id);
	/** 删除指定的 Open163Course*/	
	public boolean deleteOpen163Course(Integer id);
	/** 新增 Open163Course*/
	public boolean saveOpen163Course(Open163Course open163Course);
	/** 修改 Open163Course*/	
	public boolean updateOpen163Course(Open163Course open163Course);
	/** 更新Open163Course:id的状态 */
	public void updateStatus(int id,int status);
}