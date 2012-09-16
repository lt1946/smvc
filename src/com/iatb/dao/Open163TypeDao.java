package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Open163Type;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-04-02 23:17:06
 */
@Repository("open163TypeDao")
@SuppressWarnings("deprecation")
public class Open163TypeDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(Open163TypeDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param open163Type
	 */
	public int addReturnId(Open163Type open163Type){
		return insert("insert into open163_type (`name`,`url`,`createTime`,`status`) values(?,?,?,?)", open163Type);
	}
	/**
	 * ���
	 * @param open163_type
	 */
	public String add(Open163Type open163Type){
		String sql="insert into open163_type (`name`,`url`,`createTime`,`status`) values(?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Type.getName(),open163Type.getUrl(),open163Type.getCreateTime(),open163Type.getStatus());	
		log.info("���open163Type:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���open163Type:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param open163_type
	 */
	public boolean save(Open163Type open163Type){
		String sql="insert into open163_type (`name`,`url`,`createTime`,`status`) values(?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Type.getName(),open163Type.getUrl(),open163Type.getCreateTime(),open163Type.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param open163_type
	 */
	public boolean update(Open163Type open163Type){
		String sql="update open163_type set (`name`=?,`url`=?,`createTime`=?,`status`=?) values(?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),open163Type.getName(),open163Type.getUrl(),open163Type.getCreateTime(),open163Type.getStatus(),open163Type.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<Open163Type> listAll(){
		return listAll("open163_type", Open163Type.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("open163_type", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public Open163Type load(Integer id){
		return getById("open163_type", id, Open163Type.class);
	}
	/**
	 * ����״̬
	 * @param id
	 */
	public boolean updateStatus(Integer id,Integer status){
		return execute("update open163_type set status=? where id=?", new Object[]{status,id})>0?true:false;
	}
	
}
