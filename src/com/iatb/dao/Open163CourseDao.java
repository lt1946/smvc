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
	 * ��Ӳ�����id
	 * @param open163Course
	 */
	public int addReturnId(Open163Course open163Course){
		return insert("insert into open163_course (`name`,`openClassId`,`courseNo`,`standardSize`,`standardUrl`,`standarExt`,`mobileSize`,`mobileUrl`,`mobileExt`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)", open163Course);
	}
	/**
	 * ���
	 * @param open163_course
	 */
	public String add(Open163Course open163Course){
		String sql="insert into open163_course (`name`,`openClassId`,`courseNo`,`standardSize`,`standardUrl`,`standarExt`,`mobileSize`,`mobileUrl`,`mobileExt`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Course.getName(),open163Course.getOpenClassId(),open163Course.getCourseNo(),open163Course.getStandardSize(),open163Course.getStandardUrl(),open163Course.getStandarExt(),open163Course.getMobileSize(),open163Course.getMobileUrl(),open163Course.getMobileExt(),open163Course.getCreateTime(),open163Course.getStatus());	
		log.info("���open163Course:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���open163Course:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param open163_course
	 */
	public boolean save(Open163Course open163Course){
		String sql="insert into open163_course (`name`,`openClassId`,`courseNo`,`standardSize`,`standardUrl`,`standarExt`,`mobileSize`,`mobileUrl`,`mobileExt`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Course.getName(),open163Course.getOpenClassId(),open163Course.getCourseNo(),open163Course.getStandardSize(),open163Course.getStandardUrl(),open163Course.getStandarExt(),open163Course.getMobileSize(),open163Course.getMobileUrl(),open163Course.getMobileExt(),open163Course.getCreateTime(),open163Course.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param open163_course
	 */
	public boolean update(Open163Course open163Course){
		String sql="update open163_course set (`name`=?,`openClassId`=?,`courseNo`=?,`standardSize`=?,`standardUrl`=?,`standarExt`=?,`mobileSize`=?,`mobileUrl`=?,`mobileExt`=?,`createTime`=?,`status`=?) values(?,?,?,?,?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),open163Course.getName(),open163Course.getOpenClassId(),open163Course.getCourseNo(),open163Course.getStandardSize(),open163Course.getStandardUrl(),open163Course.getStandarExt(),open163Course.getMobileSize(),open163Course.getMobileUrl(),open163Course.getMobileExt(),open163Course.getCreateTime(),open163Course.getStatus(),open163Course.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<Open163Course> listAll(){
		return listAll("open163_course", Open163Course.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("open163_course", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public Open163Course load(Integer id){
		return getById("open163_course", id, Open163Course.class);
	}
	/**
	 * ����״̬
	 * @param id
	 */
	public boolean updateStatus(Integer id,Integer status){
		return execute("update open163_course set status=? where id=?", new Object[]{status,id})>0?true:false;
	}
	
}
