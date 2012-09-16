package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Open163University;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-04-02 23:17:06
 */
@Repository("open163UniversityDao")
@SuppressWarnings("deprecation")
public class Open163UniversityDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(Open163UniversityDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param open163University
	 */
	public int addReturnId(Open163University open163University){
		return insert("insert into open163_university (`name`,`url`,`des`,`createTime`,`status`) values(?,?,?,?,?)", open163University);
	}
	/**
	 * ���
	 * @param open163_university
	 */
	public String add(Open163University open163University){
		String sql="insert into open163_university (`name`,`url`,`des`,`createTime`,`status`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163University.getName(),open163University.getUrl(),open163University.getDes(),open163University.getCreateTime(),open163University.getStatus());	
		log.info("���open163University:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���open163University:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param open163_university
	 */
	public boolean save(Open163University open163University){
		String sql="insert into open163_university (`name`,`url`,`des`,`createTime`,`status`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163University.getName(),open163University.getUrl(),open163University.getDes(),open163University.getCreateTime(),open163University.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param open163_university
	 */
	public boolean update(Open163University open163University){
		String sql="update open163_university set (`name`=?,`url`=?,`des`=?,`createTime`=?,`status`=?) values(?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),open163University.getName(),open163University.getUrl(),open163University.getDes(),open163University.getCreateTime(),open163University.getStatus(),open163University.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<Open163University> listAll(){
		return listAll("open163_university", Open163University.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("open163_university", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public Open163University load(Integer id){
		return getById("open163_university", id, Open163University.class);
	}
	/**
	 * ����״̬
	 * @param id
	 */
	public boolean updateStatus(Integer id,Integer status){
		return execute("update open163_university set status=? where id=?", new Object[]{status,id})>0?true:false;
	}
	
}
