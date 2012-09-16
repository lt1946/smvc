package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Open163Openclasstype;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-04-02 23:17:06
 */
@Repository("open163OpenclasstypeDao")
@SuppressWarnings("deprecation")
public class Open163OpenclasstypeDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(Open163OpenclasstypeDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param open163Openclasstype
	 */
	public int addReturnId(Open163Openclasstype open163Openclasstype){
		return insert("insert into open163_openclasstype (`typeId`,`createTime`,`status`) values(?,?,?)", open163Openclasstype);
	}
	/**
	 * ���
	 * @param open163_openclasstype
	 */
	public String add(Open163Openclasstype open163Openclasstype){
		String sql="insert into open163_openclasstype (`typeId`,`createTime`,`status`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Openclasstype.getTypeId(),open163Openclasstype.getCreateTime(),open163Openclasstype.getStatus());	
		log.info("���open163Openclasstype:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���open163Openclasstype:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param open163_openclasstype
	 */
	public boolean save(Open163Openclasstype open163Openclasstype){
		String sql="insert into open163_openclasstype (`typeId`,`createTime`,`status`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),open163Openclasstype.getTypeId(),open163Openclasstype.getCreateTime(),open163Openclasstype.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param open163_openclasstype
	 */
	public boolean update(Open163Openclasstype open163Openclasstype){
		String sql="update open163_openclasstype set (`typeId`=?,`createTime`=?,`status`=?) values(?,?,?) where openClassId=?";
		int c=jdbcTemplate.update(sql.toString(),open163Openclasstype.getTypeId(),open163Openclasstype.getCreateTime(),open163Openclasstype.getStatus(),open163Openclasstype.getOpenClassId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<Open163Openclasstype> listAll(){
		return listAll("open163_openclasstype", Open163Openclasstype.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("open163_openclasstype", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public Open163Openclasstype load(Integer id){
		return getById("open163_openclasstype", id, Open163Openclasstype.class);
	}
	/**
	 * ����״̬
	 * @param id
	 */
	public boolean updateStatus(Integer id,Integer status){
		return execute("update open163_openclasstype set status=? where id=?", new Object[]{status,id})>0?true:false;
	}
	
}
