package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Open163Course;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-04-02 23:17:06
 */
@Repository("open163CourseDao")
@SuppressWarnings("deprecation")
public class Open163CourseDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(Open163CourseDao.class);
	
	/**
	 * 添加并返回id
	 * @param open163Course
	 */
	public int addReturnId(Open163Course open163Course){
		return insert("insert into open163_course (`name`,`openClassId`,`courseNo`,`standardSize`,`standardUrl`,`standarExt`,`mobileSize`,`mobileUrl`,`mobileExt`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)", open163Course);
	}
	/**
	 * 添加
	 * @param open163_course
	 */
	public String add(Open163Course open163Course){
		String sql="insert into open163_course (`name`,`openClassId`,`courseNo`,`standardSize`,`standardUrl`,`standarExt`,`mobileSize`,`mobileUrl`,`mobileExt`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Course.getName(),open163Course.getOpenClassId(),open163Course.getCourseNo(),open163Course.getStandardSize(),open163Course.getStandardUrl(),open163Course.getStandarExt(),open163Course.getMobileSize(),open163Course.getMobileUrl(),open163Course.getMobileExt(),open163Course.getCreateTime(),open163Course.getStatus());	
		log.info("添加open163Course:"+(c>0?"成功":"失败"));
		return ("添加open163Course:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param open163_course
	 */
	public boolean save(Open163Course open163Course){
		String sql="insert into open163_course (`name`,`openClassId`,`courseNo`,`standardSize`,`standardUrl`,`standarExt`,`mobileSize`,`mobileUrl`,`mobileExt`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Course.getName(),open163Course.getOpenClassId(),open163Course.getCourseNo(),open163Course.getStandardSize(),open163Course.getStandardUrl(),open163Course.getStandarExt(),open163Course.getMobileSize(),open163Course.getMobileUrl(),open163Course.getMobileExt(),open163Course.getCreateTime(),open163Course.getStatus());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param open163_course
	 */
	public boolean update(Open163Course open163Course){
		String sql="update open163_course set (`name`=?,`openClassId`=?,`courseNo`=?,`standardSize`=?,`standardUrl`=?,`standarExt`=?,`mobileSize`=?,`mobileUrl`=?,`mobileExt`=?,`createTime`=?,`status`=?) values(?,?,?,?,?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),open163Course.getName(),open163Course.getOpenClassId(),open163Course.getCourseNo(),open163Course.getStandardSize(),open163Course.getStandardUrl(),open163Course.getStandarExt(),open163Course.getMobileSize(),open163Course.getMobileUrl(),open163Course.getMobileExt(),open163Course.getCreateTime(),open163Course.getStatus(),open163Course.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<Open163Course> listAll(){
		return listAll("open163_course", Open163Course.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("open163_course", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public Open163Course load(Integer id){
		return getById("open163_course", id, Open163Course.class);
	}
	/**
	 * 更新状态
	 * @param id
	 */
	public boolean updateStatus(Integer id,Integer status){
		return execute("update open163_course set status=? where id=?", new Object[]{status,id})>0?true:false;
	}
	
}
