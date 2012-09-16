package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Open163Openclass;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-04-02 23:17:06
 */
@Repository("open163OpenclassDao")
@SuppressWarnings("deprecation")
public class Open163OpenclassDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(Open163OpenclassDao.class);
	
	/**
	 * 添加并返回id
	 * @param open163Openclass
	 */
	public int addReturnId(Open163Openclass open163Openclass){
		return insert("insert into open163_openclass (`name`,`des`,`collageId`,`lecturerId`,`courseNum`,`openClassurl`,`downUrl`,`photoUrl`,`isOver`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)", open163Openclass);
	}
	/**
	 * 添加
	 * @param open163_openclass
	 */
	public String add(Open163Openclass open163Openclass){
		String sql="insert into open163_openclass (`name`,`des`,`collageId`,`lecturerId`,`courseNum`,`openClassurl`,`downUrl`,`photoUrl`,`isOver`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Openclass.getName(),open163Openclass.getDes(),open163Openclass.getCollageId(),open163Openclass.getLecturerId(),open163Openclass.getCourseNum(),open163Openclass.getOpenClassurl(),open163Openclass.getDownUrl(),open163Openclass.getPhotoUrl(),open163Openclass.getIsOver(),open163Openclass.getCreateTime(),open163Openclass.getStatus());	
		log.info("添加open163Openclass:"+(c>0?"成功":"失败"));
		return ("添加open163Openclass:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param open163_openclass
	 */
	public boolean save(Open163Openclass open163Openclass){
		String sql="insert into open163_openclass (`name`,`des`,`collageId`,`lecturerId`,`courseNum`,`openClassurl`,`downUrl`,`photoUrl`,`isOver`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Openclass.getName(),open163Openclass.getDes(),open163Openclass.getCollageId(),open163Openclass.getLecturerId(),open163Openclass.getCourseNum(),open163Openclass.getOpenClassurl(),open163Openclass.getDownUrl(),open163Openclass.getPhotoUrl(),open163Openclass.getIsOver(),open163Openclass.getCreateTime(),open163Openclass.getStatus());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param open163_openclass
	 */
	public boolean update(Open163Openclass open163Openclass){
		String sql="update open163_openclass set (`name`=?,`des`=?,`collageId`=?,`lecturerId`=?,`courseNum`=?,`openClassurl`=?,`downUrl`=?,`photoUrl`=?,`isOver`=?,`createTime`=?,`status`=?) values(?,?,?,?,?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),open163Openclass.getName(),open163Openclass.getDes(),open163Openclass.getCollageId(),open163Openclass.getLecturerId(),open163Openclass.getCourseNum(),open163Openclass.getOpenClassurl(),open163Openclass.getDownUrl(),open163Openclass.getPhotoUrl(),open163Openclass.getIsOver(),open163Openclass.getCreateTime(),open163Openclass.getStatus(),open163Openclass.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<Open163Openclass> listAll(){
		return listAll("open163_openclass", Open163Openclass.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("open163_openclass", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public Open163Openclass load(Integer id){
		return getById("open163_openclass", id, Open163Openclass.class);
	}
	/**
	 * 更新状态
	 * @param id
	 */
	public boolean updateStatus(Integer id,Integer status){
		return execute("update open163_openclass set status=? where id=?", new Object[]{status,id})>0?true:false;
	}
	
}
