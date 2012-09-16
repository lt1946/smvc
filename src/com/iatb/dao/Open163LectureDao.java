package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Open163Lecture;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-04-02 23:17:06
 */
@Repository("open163LectureDao")
@SuppressWarnings("deprecation")
public class Open163LectureDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(Open163LectureDao.class);
	
	/**
	 * 添加并返回id
	 * @param open163Lecture
	 */
	public int addReturnId(Open163Lecture open163Lecture){
		return insert("insert into open163_lecture (`name`,`universityID`,`des`,`photoUrl`,`createTime`,`status`) values(?,?,?,?,?,?)", open163Lecture);
	}
	/**
	 * 添加
	 * @param open163_lecture
	 */
	public String add(Open163Lecture open163Lecture){
		String sql="insert into open163_lecture (`name`,`universityID`,`des`,`photoUrl`,`createTime`,`status`) values(?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Lecture.getName(),open163Lecture.getUniversityID(),open163Lecture.getDes(),open163Lecture.getPhotoUrl(),open163Lecture.getCreateTime(),open163Lecture.getStatus());	
		log.info("添加open163Lecture:"+(c>0?"成功":"失败"));
		return ("添加open163Lecture:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param open163_lecture
	 */
	public boolean save(Open163Lecture open163Lecture){
		String sql="insert into open163_lecture (`name`,`universityID`,`des`,`photoUrl`,`createTime`,`status`) values(?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Lecture.getName(),open163Lecture.getUniversityID(),open163Lecture.getDes(),open163Lecture.getPhotoUrl(),open163Lecture.getCreateTime(),open163Lecture.getStatus());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param open163_lecture
	 */
	public boolean update(Open163Lecture open163Lecture){
		String sql="update open163_lecture set (`name`=?,`universityID`=?,`des`=?,`photoUrl`=?,`createTime`=?,`status`=?) values(?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),open163Lecture.getName(),open163Lecture.getUniversityID(),open163Lecture.getDes(),open163Lecture.getPhotoUrl(),open163Lecture.getCreateTime(),open163Lecture.getStatus(),open163Lecture.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<Open163Lecture> listAll(){
		return listAll("open163_lecture", Open163Lecture.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("open163_lecture", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public Open163Lecture load(Integer id){
		return getById("open163_lecture", id, Open163Lecture.class);
	}
	/**
	 * 更新状态
	 * @param id
	 */
	public boolean updateStatus(Integer id,Integer status){
		return execute("update open163_lecture set status=? where id=?", new Object[]{status,id})>0?true:false;
	}
	
}
